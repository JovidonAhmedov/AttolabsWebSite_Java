package com.example.postgresql.dto.request;

public class VacancyLanguageRequest {
    private long language_id;
    private String level;

    public long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(long language_id) {
        this.language_id = language_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public VacancyLanguageRequest() {
    }
}
