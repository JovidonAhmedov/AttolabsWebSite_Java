package com.example.postgresql.dto.request;

public class VacancyCityRequest1 {
    private long city_id;
    private long vacancy_id;

    private boolean status;

    public VacancyCityRequest1(long city_id, long vacancy_id, boolean status) {
        this.city_id = city_id;
        this.vacancy_id = vacancy_id;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public long getVacancy_id() {
        return vacancy_id;
    }

    public void setVacancy_id(long vacancy_id) {
        this.vacancy_id = vacancy_id;
    }
}
