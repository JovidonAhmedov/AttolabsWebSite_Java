package com.example.postgresql.dto.request;

public class VacancyFilterRequest {
    private long citiId;
    private long countryId;
    private long groupId;

    public long getCitiId() {
        return citiId;
    }

    public void setCitiId(long citiId) {
        this.citiId = citiId;
    }

    public long getCountryId() {
        return countryId;
    }

    public void setCountryId(long countryId) {
        this.countryId = countryId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
