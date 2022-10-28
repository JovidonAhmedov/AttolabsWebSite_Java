package com.example.postgresql.mapper;

import com.example.postgresql.dto.request.RequirementRequest;
import com.example.postgresql.dto.request.VacancyCityRequest;
import com.example.postgresql.dto.response.LanguageResponse;
import com.example.postgresql.dto.response.VacancyCityResponse;
import com.example.postgresql.dto.response.VacancyCityResponse1;
import com.example.postgresql.model.Requirement;
import com.example.postgresql.model.Vacancy;
import com.example.postgresql.model.VacancyCity;
import com.example.postgresql.model.VacancyLanguage;

import java.util.ArrayList;
import java.util.List;

public class VacancyCityMapper {
    public static VacancyCityResponse1 VacancyCityToVacancyCityResponse(VacancyCity vacancyCity)
    {
        VacancyCityResponse1 result=new VacancyCityResponse1();

        result.setId(vacancyCity.getId());

        result.setCity_id(vacancyCity.getCity().getId());
        result.setCity_name(vacancyCity.getCity().getName());
        result.setCountry_id(vacancyCity.getCity().getCountry().getId());
        result.setCountry_name(vacancyCity.getCity().getCountry().getName());

        result.setVacancy_id(vacancyCity.getVacancy().getId());
        result.setVacancy_title(vacancyCity.getVacancy().getTitle());

        result.setStatus(vacancyCity.isVacancy_status());

        result.setLanguages(null);
        result.setRequirements(null);

        return result;
    }

    public static VacancyCity ToVacancyCity(VacancyCityRequest request) {
        VacancyCity vacancyCity=new VacancyCity();
        if(request.isVacancy_status())
            vacancyCity.setVacancy_status(true);
        else
            vacancyCity.setVacancy_status(false);


        return vacancyCity;
    }

    public static VacancyCityResponse ToVacancyCityResponse(VacancyCity vacancyCity)
    {
        VacancyCityResponse response=new VacancyCityResponse();

        if(vacancyCity.isVacancy_status())
            response.setVacancy_status(true);
        else
            response.setVacancy_status(false);

        response.setCity_name(vacancyCity.getCity().getName());
        response.setCountry_name(vacancyCity.getCity().getCountry().getName());

        return response;
    }

    public static List<VacancyCityResponse> ToVacancyCityResponses(List<VacancyCity> vacancyCities)
    {
        List<VacancyCityResponse> responses=new ArrayList<>();

        for (VacancyCity vacancyCity: vacancyCities) {
            responses.add(ToVacancyCityResponse(vacancyCity));
        }
        return responses;
    }

    public static List<VacancyCity> ToVacancyCities(List<VacancyCityRequest> requests, Vacancy vacancy) {
        List<VacancyCity> vacancyCities=new ArrayList<>();
        for (VacancyCityRequest request:requests) {
            vacancyCities.add(ToVacancyCity(request, vacancy));
        }
        return vacancyCities;
    }

    public static VacancyCity ToVacancyCity(VacancyCityRequest request, Vacancy vacancy) {
        VacancyCity vacancyCity=new VacancyCity();
        if(request.isVacancy_status())
            vacancyCity.setVacancy_status(true);
        else
            vacancyCity.setVacancy_status(false);

        vacancyCity.setVacancy(vacancy);

        return vacancyCity;
    }
}
