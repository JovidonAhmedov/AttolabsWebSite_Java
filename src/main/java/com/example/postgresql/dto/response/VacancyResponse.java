package com.example.postgresql.dto.response;

import java.util.ArrayList;
import java.util.List;

public class VacancyResponse {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String title;
    private String type;
    private String description;
    private String condition;
    private String quarantee;
    private String vacancy_group;
    private List<RequirementResponse> requirements=new ArrayList<>();
    private List<LanguageResponse> vacancyLanguages=new ArrayList<>();
    private List<VacancyCityResponse> vacancyCities =new ArrayList<>();

    public List<VacancyCityResponse> getVacancyCities() {
        return vacancyCities;
    }

    public void setVacancyCities(List<VacancyCityResponse> vacancyCities) {
        this.vacancyCities = vacancyCities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getQuarantee() {
        return quarantee;
    }

    public void setQuarantee(String quarantee) {
        this.quarantee = quarantee;
    }

    public String getVacancy_group() {
        return vacancy_group;
    }

    public void setVacancy_group(String vacancy_group) {
        this.vacancy_group = vacancy_group;
    }

    public List<RequirementResponse> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementResponse> requirements) {
        this.requirements = requirements;
    }

    public List<LanguageResponse> getVacancyLanguages() {
        return vacancyLanguages;
    }

    public void setVacancyLanguages(List<LanguageResponse> vacancyLanguages) {
        this.vacancyLanguages = vacancyLanguages;
    }

    public VacancyResponse() {
    }
}
