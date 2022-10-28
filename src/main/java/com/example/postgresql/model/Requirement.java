package com.example.postgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="vacancy_id",referencedColumnName = "id")
    private Vacancy vacancy;

//    public Vacancy getVacancy() {
//        return vacancy;
//    }

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

    public Requirement(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public Requirement() {
    }

    public Requirement(String title) {
        this.title = title;
    }

    public void assignVacancy(Vacancy vacancy) {
        this.vacancy=vacancy;
    }
}
