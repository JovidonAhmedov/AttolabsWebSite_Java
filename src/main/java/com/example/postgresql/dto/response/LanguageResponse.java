package com.example.postgresql.dto.response;

public class LanguageResponse {
    private String language_name;
    private String level;


    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public LanguageResponse() {
    }
}
