package com.example.postgresql.dto.request;

public class CityRequest {
    private String name;
    private long country_id;

    public CityRequest(String name, long country_id) {
        this.name = name;
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(long country_id) {
        this.country_id = country_id;
    }
}
