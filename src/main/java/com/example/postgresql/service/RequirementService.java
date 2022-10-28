package com.example.postgresql.service;

import com.example.postgresql.dto.response.Response;
import com.example.postgresql.model.City;
import com.example.postgresql.model.Country;
import com.example.postgresql.model.Requirement;
import com.example.postgresql.model.Vacancy;
import com.example.postgresql.repository.RequirementRepository;
import com.example.postgresql.repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {
    private final RequirementRepository requirementRepository;
    private final VacancyRepository vacancyRepository;

    public RequirementService(RequirementRepository requirementRepository, VacancyRepository vacancyRepository) {
        this.requirementRepository = requirementRepository;
        this.vacancyRepository = vacancyRepository;
    }

    public List<Requirement> getRequirements()
    {
        return requirementRepository.findAll();
    }

    public Requirement getRequirement(long id)
    {
        if(requirementRepository.existsById(id)) {
            return requirementRepository.findById(id).get();
        }
        return null;
    }

    public Response createRequirement(Requirement requirement)
    {
        Response response=null;
        try {
            requirementRepository.save(requirement);
            response=new Response("Новое требование успешно создано.",200);
        }
        catch (Exception ex)
        {
            response=new Response("Ошибка во время создание.",-1);
        }
        return response;
    }

    public Response deleteRequirement(long id)
    {
        if(requirementRepository.existsById(id))
        {
            try {
                requirementRepository.deleteById(id);
                return new Response("Успешно удалено",1);
            }
            catch(Exception ex)
            {
                return new Response("Ошибка во время удаление.",-1);
            }
        }
        return new Response("Требование не найдено.",0);
    }

    public Response updateRequirement(long id, Requirement newRequirement)
    {
        if(requirementRepository.existsById(id)) {
            try {
                Requirement requirement = requirementRepository.findById(id).get();
                if(newRequirement.getTitle()!=null) {
                    requirement.setTitle(newRequirement.getTitle());
                }
                requirementRepository.save(requirement);
                return new Response("Успешно изменен",1);
            }
            catch (Exception ex){
                return new Response("Ошибка во время изменение.",-1);
            }
        }
        return new Response("Требование не найдено.",0);
    }

    public Response assignVacancyToRequirement(long requirementId, long vacancyId)
    {
        Response response;
        Requirement requirement=null;
        if(requirementRepository.existsById(requirementId)) {
            requirement = requirementRepository.findById(requirementId).get();
        }
        Vacancy vacancy=null;
        if(vacancyRepository.existsById(vacancyId))
            vacancy=vacancyRepository.findById(vacancyId).get();
        if(requirement!=null)
        {
            if(vacancy!=null)
            {
                requirement.assignVacancy(vacancy);
                requirementRepository.save(requirement);
                response=new Response("Успешно изменен",200);
            }
            else
            {
                response=new Response("Ошибка. Ваканция не найдена!",0);
            }
        }
        else
        {
            response=new Response("Ошибка. Требование не найдено.",0);
        }

        return response;
    }

    public Response assignVacancyToRequirements(List<Requirement> requirements, long vacancyId)
    {
        Response response;
        Vacancy vacancy=null;
        if(vacancyRepository.existsById(vacancyId))
            vacancy=vacancyRepository.findById(vacancyId).get();
            if(vacancy!=null)
            {
                for (Requirement requirement:requirements) {
                    requirement.assignVacancy(vacancy);
                    requirementRepository.save(requirement);
                }
                response=new Response("Успешно изменен",200);
            }
            else
            {
                response=new Response("Ошибка. Ваканция не найдена!",0);
            }

        return response;
    }
}
