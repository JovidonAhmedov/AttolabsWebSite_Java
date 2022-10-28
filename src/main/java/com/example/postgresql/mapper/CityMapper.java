package com.example.postgresql.mapper;

import com.example.postgresql.dto.request.CityRequest;
import com.example.postgresql.model.City;

public class CityMapper {
    public static City ToCity(CityRequest request)
    {
        return new City(0,request.getName());
    }
}
