package com.example.postgresql.dto.response;

import java.util.ArrayList;
import java.util.List;

public class VacancyCityResponse1 {
    private Long id;
    private Boolean status;
    private long vacancy_id;
    private String vacancy_title;
    private long city_id;
    private String city_name;
    private long country_id;
    private String country_name;

    private List<RequirementResponse> requirements=new ArrayList<>();
    private List<LanguageResponse> languages=new ArrayList<>();




    public List<RequirementResponse> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementResponse> requirements) {
        this.requirements = requirements;
    }

    public List<LanguageResponse> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageResponse> languages) {
        this.languages = languages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public long getVacancy_id() {
        return vacancy_id;
    }

    public void setVacancy_id(long vacancy_id) {
        this.vacancy_id = vacancy_id;
    }

    public String getVacancy_title() {
        return vacancy_title;
    }

    public void setVacancy_title(String vacancy_title) {
        this.vacancy_title = vacancy_title;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(long country_id) {
        this.country_id = country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public VacancyCityResponse1() {
    }
}
