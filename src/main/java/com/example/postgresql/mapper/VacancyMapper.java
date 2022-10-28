package com.example.postgresql.mapper;

import com.example.postgresql.dto.request.VacancyRequest;
import com.example.postgresql.dto.response.VacancyResponse;
import com.example.postgresql.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class VacancyMapper {

    public static Vacancy ToVacancy(VacancyRequest request)
    {
        Vacancy vacancy=new Vacancy();
        vacancy.setCondition(request.getCondition());
        vacancy.setTitle(request.getTitle());
        vacancy.setDescription(request.getDescription());
        vacancy.setQuarantee(request.getQuarantee());
        vacancy.setType(request.getType());

        return vacancy;
    }

    public static VacancyResponse ToVacancyResponse(Vacancy vacancy)
    {
        VacancyResponse vacancyResponse=new VacancyResponse();
        vacancyResponse.setId(vacancy.getId());
        vacancyResponse.setCondition(vacancy.getCondition());
        vacancyResponse.setDescription(vacancy.getDescription());
        vacancyResponse.setQuarantee(vacancy.getQuarantee());

        vacancyResponse.setTitle(vacancy.getTitle());
        vacancyResponse.setType(vacancy.getType());

        if(vacancy.getVacancy_group()!=null)
            vacancyResponse.setVacancy_group(vacancy.getVacancy_group().getName());

        vacancyResponse.setRequirements(RequirementMapper.ToRequirementResponses(vacancy.getRequirements()));
        vacancyResponse.setVacancyLanguages(VacancyLanguageMapper.ToVacancyLanguageResponses(vacancy.getVacancyLanguages()));
        vacancyResponse.setVacancyCities(VacancyCityMapper.ToVacancyCityResponses(vacancy.getVacancyCities()));
        return vacancyResponse;
    }

    public static List<VacancyResponse> ToVacancyResponses(List<Vacancy> vacancies)
    {
        List<VacancyResponse> responses=new ArrayList<>();

        for (Vacancy vacancy:vacancies) {
            responses.add(ToVacancyResponse(vacancy));
        }
        return responses;
    }
}
