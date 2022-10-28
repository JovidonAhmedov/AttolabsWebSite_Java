package com.example.postgresql.mapper;

import com.example.postgresql.dto.request.VacancyCityRequest;
import com.example.postgresql.dto.request.VacancyLanguageRequest;
import com.example.postgresql.dto.response.LanguageResponse;
import com.example.postgresql.dto.response.RequirementResponse;
import com.example.postgresql.model.Requirement;
import com.example.postgresql.model.Vacancy;
import com.example.postgresql.model.VacancyCity;
import com.example.postgresql.model.VacancyLanguage;

import java.util.ArrayList;
import java.util.List;

public class VacancyLanguageMapper {
    public static VacancyLanguage ToVacancyLanguage(VacancyLanguageRequest request)
    {
        VacancyLanguage vacancyLanguage=new VacancyLanguage();
        vacancyLanguage.setLevel(request.getLevel());
        return vacancyLanguage;
    }

    public static LanguageResponse ToVacancyLanguageResponse(VacancyLanguage vacancyLanguage)
    {
        LanguageResponse response=new LanguageResponse();
        response.setLanguage_name(vacancyLanguage.getLanguage().getName());
        response.setLevel(vacancyLanguage.getLevel());
        return response;
    }

    public static List<LanguageResponse> ToVacancyLanguageResponses(List<VacancyLanguage> vacancyLanguages)
    {
        List<LanguageResponse> responses=new ArrayList<>();

        for (VacancyLanguage vacancyLanguage: vacancyLanguages) {
            responses.add(ToVacancyLanguageResponse(vacancyLanguage));
        }
        return responses;
    }

    public static List<VacancyLanguage> ToVacancyLanguages(List<VacancyLanguageRequest> requests, Vacancy vacancy) {
        List<VacancyLanguage> vacancyLanguages=new ArrayList<>();
        for (VacancyLanguageRequest request:requests) {
            vacancyLanguages.add(ToVacancyCity(request, vacancy));
        }
        return vacancyLanguages;
    }

    public static VacancyLanguage ToVacancyCity(VacancyLanguageRequest request, Vacancy vacancy) {
        VacancyLanguage vacancyLanguage=new VacancyLanguage();
        vacancyLanguage.setLevel(request.getLevel());
        vacancyLanguage.setVacancy(vacancy);
        return vacancyLanguage;
    }
}
