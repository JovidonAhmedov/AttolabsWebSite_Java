package com.example.postgresql.dto.request;

public class VacancyCityRequest {
    private long city_id;
    private boolean vacancy_status;

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }



    public void setVacancy_status(boolean vacancy_status) {
        this.vacancy_status = vacancy_status;
    }
    public boolean isVacancy_status() {
        return vacancy_status;
    }

    public VacancyCityRequest() {
    }
}
