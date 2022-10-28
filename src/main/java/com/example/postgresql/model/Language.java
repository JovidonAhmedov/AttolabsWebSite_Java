package com.example.postgresql.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "language")
    List<VacancyLanguage> vacancyLanguages;

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
    public Language(long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Language() {
    }
}
