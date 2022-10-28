package com.example.postgresql.service;

import com.example.postgresql.dto.request.*;
import com.example.postgresql.dto.response.Response;
import com.example.postgresql.mapper.RequirementMapper;
import com.example.postgresql.mapper.VacancyCityMapper;
import com.example.postgresql.mapper.VacancyLanguageMapper;
import com.example.postgresql.mapper.VacancyMapper;
import com.example.postgresql.model.*;
import com.example.postgresql.repository.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacancyService {
    private final VacancyRepository vacancyRepository;
    private final VacancyLanguageRepository vacancyLanguageRepository;
    private final LanguageRepository languageRepository;
    private final VacancyGroupRepository vacancyGroupRepository;
    private final RequirementRepository requirementRepository;
    private final CityRepository cityRepository;
    private final VacancyCityRepository vacancyCityRepository;

    public VacancyService(VacancyRepository vacancyRepository, VacancyLanguageRepository vacancyLanguageRepository,
                          LanguageRepository languageRepository, VacancyGroupRepository vacancyGroupRepository,
                          RequirementRepository requirementRepository, CityRepository cityRepository,
                          VacancyCityRepository vacancyCityRepository) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyLanguageRepository = vacancyLanguageRepository;
        this.languageRepository = languageRepository;
        this.vacancyGroupRepository = vacancyGroupRepository;
        this.requirementRepository = requirementRepository;
        this.cityRepository = cityRepository;
        this.vacancyCityRepository = vacancyCityRepository;
    }

    public List<Vacancy> getVacancies() {
        return vacancyRepository.findAll();
    }

    public List<Vacancy> getFilteredVacancies(VacancyFilterRequest request) {
        List<Vacancy> vacancies = filterVacancy(vacancyRepository.findAll());

        if (request.getCountryId() > 0)
            vacancies = vacancies.stream().filter(c -> c.getVacancyCities().stream().filter(e -> e.getCity().getCountry().getId() == request.getCountryId()).count() > 0).toList();

        if (request.getCitiId() > 0)
            vacancies = vacancies.stream().filter(c -> c.getVacancyCities().stream().filter(e -> e.getCity().getId() == request.getCitiId()).count() > 0).toList();

        if (request.getGroupId() > 0)
            vacancies = vacancies.stream().filter(c -> c.getVacancy_group().getId() == request.getGroupId()).toList();

        return vacancies;
    }

    public List<Vacancy> getVacanciesForEachCity() {
        List<Vacancy> filteredVacancies = new ArrayList<>();
        filteredVacancies = filterVacancy(vacancyRepository.findAll());
        return filteredVacancies;
    }

    private List<Vacancy> filterVacancy(List<Vacancy> vacancies)
    {
        List<Vacancy> filteredVacancies = new ArrayList<>();
        for (Vacancy vacancy : vacancies) {
            try {
                for (VacancyCity vacancyCity : vacancy.getVacancyCities()) {
                    Vacancy filteredVacancy = vacancy.clone();
                    VacancyCity vacancyCityClone = vacancyCity.clone();
                    List<VacancyCity> vacancyCitiesClone = new ArrayList<>();
                    vacancyCitiesClone.add(vacancyCityClone);
                    filteredVacancy.setVacancyCities(vacancyCitiesClone);
                    filteredVacancies.add(filteredVacancy);
                }
            } catch (Exception ex) {

            }
        }
        return filteredVacancies;
    }
    public Vacancy getVacancy(long id) {
        if (vacancyRepository.existsById(id)) {
            return vacancyRepository.findById(id).get();
        }
        return null;
    }

    public Response createVacancy(VacancyRequest request) {
        Response response = null;
        try {
            Vacancy vacancy = VacancyMapper.ToVacancy(request);

            vacancy = vacancyRepository.save(vacancy);


//            List<Requirement> requirements= RequirementMapper.ToRequirements(request.getRequirements(), vacancy);
//            List<VacancyCity> vacancyCities= VacancyCityMapper.ToVacancyCities(request.getVacancyCities(), vacancy);
//            List<VacancyLanguage> vacancyLanguages= VacancyLanguageMapper.ToVacancyLanguages(request.getVacancyLanguages(), vacancy);
//
//            vacancy.setVacancyCities(vacancyCities);
//            vacancy.setVacancyLanguages(vacancyLanguages);
//            vacancy.setRequirements(requirements);

            createVacancyChildren(request, vacancy);
            vacancyRepository.save(vacancy);

            response = new Response("Новая ваканция успешно создана.", 200);
        } catch (Exception ex) {
            response = new Response("Ошибка во время создание.", -1);
        }
        return response;
    }

    public Response deleteVacancy(long id) {
        if (vacancyRepository.existsById(id)) {
            try {

                vacancyRepository.deleteById(id);

                return new Response("Успешно удалено", 1);
            } catch (Exception ex) {
                return new Response("Ошибка во время удаление.", -1);
            }
        }
        return new Response("Ваканция не найдена.", 0);
    }

    public Response updateVacancy(long id, VacancyRequest newVacancy) {
        if (vacancyRepository.existsById(id)) {
            try {
                Vacancy vacancy = vacancyRepository.findById(id).get();

                deleteVacancyChildren(vacancy);
                createVacancyChildren(newVacancy, vacancy);

                vacancy.setType(newVacancy.getType());
                vacancy.setQuarantee(newVacancy.getQuarantee());
                vacancy.setDescription(newVacancy.getDescription());
                vacancy.setTitle(newVacancy.getTitle());
                vacancy.setCondition(newVacancy.getCondition());

                if (vacancyGroupRepository.existsById(newVacancy.getVacancy_group_id())) {
                    VacancyGroup vacancyGroup = vacancyGroupRepository.findById(newVacancy.getVacancy_group_id()).get();
                    vacancy.setVacancy_group(vacancyGroup);
                }

                vacancyRepository.save(vacancy);

                return new Response("Успешно изменена", 1);
            } catch (Exception ex) {
                return new Response("Ошибка во время изменение.", -1);
            }
        }
        return new Response("Ваканция не найдена.", 0);
    }

    private void createVacancyChildren(VacancyRequest request, Vacancy vacancy) {
        List<VacancyLanguage> vacancyLanguages = new ArrayList<>();
        for (VacancyLanguageRequest item : request.getVacancyLanguages()) {
            VacancyLanguage vacancyLanguage = VacancyLanguageMapper.ToVacancyLanguage(item);
            vacancyLanguage.setVacancy(vacancy);

            if (languageRepository.existsById(item.getLanguage_id())) {
                vacancyLanguage.setLanguage(languageRepository.findById(item.getLanguage_id()).get());
                vacancyLanguage = vacancyLanguageRepository.save(vacancyLanguage);
                vacancyLanguages.add(vacancyLanguage);
            }
        }
        vacancy.setVacancyLanguages(vacancyLanguages);

        List<Requirement> requirements = new ArrayList<>();
        for (RequirementRequest item : request.getRequirements()) {
            Requirement requirement = new Requirement(item.getTitle());
            requirement.setVacancy(vacancy);

            requirement = requirementRepository.save(requirement);
            requirements.add(requirement);
        }
        vacancy.setRequirements(requirements);

        List<VacancyCity> vacancyCities = new ArrayList<>();
        for (VacancyCityRequest item : request.getVacancyCities()) {
            VacancyCity vacancyCity = VacancyCityMapper.ToVacancyCity(item);
            vacancyCity.setVacancy(vacancy);

            if (cityRepository.existsById(item.getCity_id())) {
                vacancyCity.setCity(cityRepository.findById(item.getCity_id()).get());
                vacancyCity = vacancyCityRepository.save(vacancyCity);
                vacancyCities.add(vacancyCity);
            }
        }
        vacancy.setVacancyCities(vacancyCities);

        if (vacancyGroupRepository.existsById(request.getVacancy_group_id())) {
            VacancyGroup vacancyGroup = vacancyGroupRepository.findById(request.getVacancy_group_id()).get();
            vacancy.setVacancy_group(vacancyGroup);
        }
    }

    private void deleteVacancyChildren(Vacancy vacancy) {
        for (VacancyCity vacancyCity : vacancy.getVacancyCities()) {
            vacancyCity.setVacancy(null);
            vacancyCityRepository.save(vacancyCity);
            vacancyCityRepository.delete(vacancyCity);
        }

        for (Requirement requirement : vacancy.getRequirements()) {
            requirement.setVacancy(null);
            requirementRepository.save(requirement);
            requirementRepository.delete(requirement);
        }
        for (VacancyLanguage vacancyLanguage : vacancy.getVacancyLanguages()) {
            vacancyLanguage.setVacancy(null);
            vacancyLanguageRepository.save(vacancyLanguage);
            vacancyLanguageRepository.delete(vacancyLanguage);
        }
    }
}
