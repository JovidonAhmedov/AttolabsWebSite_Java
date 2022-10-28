package com.example.postgresql.controller;

import com.example.postgresql.dto.request.CityRequest;
import com.example.postgresql.dto.response.Response;
import com.example.postgresql.model.VacancyGroup;
import com.example.postgresql.service.VacancyGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancygroup")

public class VacancyGroupController {
    private final VacancyGroupService vacancyGroupService;

    public VacancyGroupController(VacancyGroupService vacancyGroupService) {
        this.vacancyGroupService = vacancyGroupService;
    }

    @GetMapping
    public ResponseEntity getVacancyGroups(){
        return ResponseEntity.ok(vacancyGroupService.getVacancyGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity getVacancyGroup(@PathVariable long id){
        return ResponseEntity.ok(vacancyGroupService.getVacancyGroup(id));
    }

    @PostMapping
    public ResponseEntity createVacancyGroup(@RequestBody VacancyGroup vacancyGroup){

        Response response=vacancyGroupService.createVacancyGroup(vacancyGroup);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVacancyGroup(@PathVariable long id){
        Response response=vacancyGroupService.deleteVacancyGroup(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateVacancyGroup(@PathVariable Long id, @RequestBody VacancyGroup newVacancyGroup){

        return ResponseEntity.ok(vacancyGroupService.updateVacancyGroup(id, newVacancyGroup));
    }
}
