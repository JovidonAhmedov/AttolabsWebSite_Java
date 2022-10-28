package com.example.postgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class VacancyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "vacancy_group")
    private List<Vacancy> vacancies=new ArrayList<>();

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public VacancyGroup(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public VacancyGroup() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
