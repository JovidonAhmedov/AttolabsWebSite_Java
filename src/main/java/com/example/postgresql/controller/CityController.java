package com.example.postgresql.controller;

import com.example.postgresql.dto.request.CityRequest;
import com.example.postgresql.dto.response.Response;
import com.example.postgresql.service.VacancyLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/city")
public class CityController {

    private final VacancyLocationService vacancyLocationService;

    public CityController(VacancyLocationService vacancyLocationService) {
        this.vacancyLocationService = vacancyLocationService;
    }

    @GetMapping
    public ResponseEntity getCities(){
        return ResponseEntity.ok(vacancyLocationService.getCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCity(@PathVariable long id){
        return ResponseEntity.ok(vacancyLocationService.getCity(id));
    }

    @PostMapping
    public ResponseEntity createCity(@RequestBody CityRequest request)
    {
        Response response=vacancyLocationService.createCity(request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCity(@PathVariable Long id)
    {
        Response response=vacancyLocationService.deleteCity(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCity(@PathVariable Long id, @RequestBody CityRequest newCity){

        return ResponseEntity.ok(vacancyLocationService.updateCity(id, newCity));
    }

    @PutMapping("/{cityId}/country/{countryId}")
    public ResponseEntity assignCountryToCity(@PathVariable Long cityId, @PathVariable Long countryId){

        return ResponseEntity.ok(vacancyLocationService.assignCountryToCity(cityId, countryId));
    }
}
