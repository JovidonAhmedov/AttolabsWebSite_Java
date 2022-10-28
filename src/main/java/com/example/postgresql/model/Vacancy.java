package com.example.postgresql.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vacancy implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String type;
    private String description ;
    private String condition;
    private String quarantee;

    @OneToMany(mappedBy = "vacancy",cascade = CascadeType.ALL)
    private List<VacancyCity> vacancyCities;

    @OneToMany(mappedBy = "vacancy",cascade = CascadeType.ALL)
    private List<VacancyLanguage> vacancyLanguages;

    @ManyToOne()
    @JoinColumn(name="vacancy_group_id",referencedColumnName = "id")
    private VacancyGroup vacancy_group;


    @OneToMany(mappedBy = "vacancy", fetch = FetchType.EAGER, cascade = CascadeType.ALL )
    private List<Requirement> requirements=new ArrayList<>();


    public List<VacancyCity> getVacancyCities() {
        return vacancyCities;
    }

    public void setVacancyCities(List<VacancyCity> vacancyCities) {
        this.vacancyCities = vacancyCities;
    }

    public void setVacancyLanguages(List<VacancyLanguage> vacancyLanguages) {
        this.vacancyLanguages = vacancyLanguages;
    }

    public void setVacancy_group(VacancyGroup vacancy_group) {
        this.vacancy_group = vacancy_group;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public VacancyGroup getVacancy_group() {
        return vacancy_group;
    }


    public List<VacancyLanguage> getVacancyLanguages() {
        return vacancyLanguages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Vacancy clone() throws CloneNotSupportedException{
        return (Vacancy)super.clone();
    }
    public Vacancy() {
    }
}
