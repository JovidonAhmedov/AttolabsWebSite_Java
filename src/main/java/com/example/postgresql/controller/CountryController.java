package com.example.postgresql.controller;

import com.example.postgresql.dto.request.CityRequest;
import com.example.postgresql.dto.response.Response;
import com.example.postgresql.model.City;
import com.example.postgresql.model.Country;
import com.example.postgresql.repository.CountryRepository;
import com.example.postgresql.service.VacancyLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
public class CountryController {
    private final VacancyLocationService vacancyLocationService;

    public CountryController(VacancyLocationService vacancyLocationService)
    {
        this.vacancyLocationService = vacancyLocationService;
    }

    @GetMapping
    public ResponseEntity getCountries()
    {
        return ResponseEntity.ok(vacancyLocationService.getCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCountry(@PathVariable long id)
    {
        return ResponseEntity.ok(vacancyLocationService.getCountry(id));
    }

    @PostMapping
    public ResponseEntity createCountry(@RequestBody Country request)
    {
        Response response=vacancyLocationService.createCountry(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCountry(@PathVariable Long id)
    {
        Response response=vacancyLocationService.deleteCountry(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCountry(@PathVariable Long id, @RequestBody Country newCountry)
    {
        return ResponseEntity.ok(vacancyLocationService.updateCountry(id, newCountry));
    }
}
