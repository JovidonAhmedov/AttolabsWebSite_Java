package com.example.postgresql.controller;

import com.example.postgresql.dto.request.VacancyCityRequest1;
import com.example.postgresql.dto.response.Response;
import com.example.postgresql.dto.response.VacancyCityResponse1;
import com.example.postgresql.model.VacancyCity;
import com.example.postgresql.service.VacancyLocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancycity")
public class VacancyCityController {
    private final VacancyLocationService vacancyLocationService;

    public VacancyCityController(VacancyLocationService vacancyLocationService) {
        this.vacancyLocationService = vacancyLocationService;
    }

    @GetMapping
    public ResponseEntity getVacancyCities()
    {
        List<VacancyCity> vacancyCities=vacancyLocationService.getVacancyCities();
        VacancyCityResponse1 response=new VacancyCityResponse1();

        return ResponseEntity.ok(vacancyLocationService.getVacancyCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity getVacancyCity(@PathVariable long id){
        return ResponseEntity.ok(vacancyLocationService.getVacancyCity(id));
    }


    @PostMapping
    public ResponseEntity createVacancyCity(@RequestBody VacancyCityRequest1 request)
    {
        return ResponseEntity.ok(vacancyLocationService.createVacancyCity(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVacancyCity(@PathVariable Long id)
    {
        Response response=vacancyLocationService.deleteVacancyCity(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}")
    public ResponseEntity updateVacancyCity(@PathVariable long id, @RequestBody VacancyCityRequest1 request)
    {
        return ResponseEntity.ok(vacancyLocationService.updateVacancyCity(id,request));
    }

}
