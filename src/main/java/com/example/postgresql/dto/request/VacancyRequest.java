package com.example.postgresql.dto.request;

import com.example.postgresql.model.Requirement;
import com.example.postgresql.model.VacancyLanguage;
import java.util.ArrayList;
import java.util.List;

public class VacancyRequest {
    private String title;
    private String type;
    private String description;
    private String condition;
    private String quarantee;
    private boolean vacancy_status;
    private long vacancy_group_id;
    private List<RequirementRequest> requirements=new ArrayList<>();
    private List<VacancyLanguageRequest> vacancyLanguages=new ArrayList<>();

    private List<VacancyCityRequest> vacancyCities=new ArrayList<>();


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

    public boolean isVacancy_status() {
        return vacancy_status;
    }

    public void setVacancy_status(boolean vacancy_status) {
        this.vacancy_status = vacancy_status;
    }

    public List<VacancyCityRequest> getVacancyCities() {
        return vacancyCities;
    }

    public void setVacancyCities(List<VacancyCityRequest> vacancyCities) {
        this.vacancyCities = vacancyCities;
    }

    public long getVacancy_group_id() {
        return vacancy_group_id;
    }

    public void setVacancy_group_id(long vacancy_group_id) {
        this.vacancy_group_id = vacancy_group_id;
    }

    public List<RequirementRequest> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<RequirementRequest> requirements) {
        this.requirements = requirements;
    }

    public List<VacancyLanguageRequest> getVacancyLanguages() {
        return vacancyLanguages;
    }

    public void setVacancyLanguages(List<VacancyLanguageRequest> vacancyLanguages) {
        this.vacancyLanguages = vacancyLanguages;
    }
}
