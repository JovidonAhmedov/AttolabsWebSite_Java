package com.example.postgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
@Entity
public class VacancyCity implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean vacancy_status=true;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private Vacancy vacancy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public boolean isVacancy_status() {
        return vacancy_status;
    }

    public void setVacancy_status(boolean vacncy_status) {
        this.vacancy_status = vacncy_status;
    }

    public void assignVacancyToVacancyCity(Vacancy vacancy) {
        this.vacancy=vacancy;
    }

    public void assignCityToVacancyCity(City city) {
        this.city=city;
    }

    public VacancyCity() {
    }
    public VacancyCity clone() throws CloneNotSupportedException{
        return (VacancyCity) super.clone();
    }
}
