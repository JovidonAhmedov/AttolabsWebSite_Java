package com.example.postgresql.dto.response;

import com.example.postgresql.model.City;
import com.example.postgresql.model.Vacancy;

public class VacancyCityResponse {

    private boolean vacancy_status;
    private String city_name;
    private String country_name;

    public boolean isVacancy_status() {
        return vacancy_status;
    }

    public void setVacancy_status(boolean vacancy_status) {
        this.vacancy_status = vacancy_status;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public VacancyCityResponse() {
    }
}
