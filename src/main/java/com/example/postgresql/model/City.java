package com.example.postgresql.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @ManyToOne
    @JoinColumn(name="country_id",referencedColumnName = "id")
    private Country country;

    public void setCountry(Country country) {
        this.country = country;
    }

    public City() {
    }

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<VacancyCity> vacancyCities=new ArrayList<>();

    public Country getCountry() {
        return country;
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

    public List<VacancyCity> getVacancyCities() {
        return vacancyCities;
    }

    public void setVacancyCities(List<VacancyCity> vacancyCities) {
        this.vacancyCities = vacancyCities;
    }

    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void assignCountry(Country country) {
        this.country=country;
    }
}
