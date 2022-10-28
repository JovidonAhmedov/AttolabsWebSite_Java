package com.example.postgresql.controller;

import com.example.postgresql.dto.request.VacancyFilterRequest;
import com.example.postgresql.dto.request.VacancyRequest;
import com.example.postgresql.dto.response.Response;
import com.example.postgresql.dto.response.VacancyResponse;
import com.example.postgresql.mapper.VacancyMapper;
import com.example.postgresql.model.Vacancy;
import com.example.postgresql.repository.RequirementRepository;
import com.example.postgresql.service.VacancyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;
    private final RequirementRepository requirementRepository;

    public VacancyController(VacancyService vacancyService, RequirementRepository requirementRepository) {
        this.vacancyService = vacancyService;
        this.requirementRepository = requirementRepository;
    }

    @GetMapping("/admin")
    public ResponseEntity getVacanciesForAdmin()
    {
        List<VacancyResponse> responses= VacancyMapper.ToVacancyResponses(vacancyService.getVacancies());
        return ResponseEntity.ok(responses);
    }
    @GetMapping
    public ResponseEntity getVacancies()
    {
        List<VacancyResponse> responses= VacancyMapper.ToVacancyResponses(vacancyService.getVacanciesForEachCity());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity getVacancy(@PathVariable long id){

        VacancyResponse response=VacancyMapper.ToVacancyResponse(vacancyService.getVacancy(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/filter")
    public ResponseEntity createVacancy(@RequestBody VacancyFilterRequest request)
    {
        List<VacancyResponse> responses= VacancyMapper.ToVacancyResponses(vacancyService.getFilteredVacancies(request));
        return ResponseEntity.ok(responses);

    }

    @PostMapping
    public ResponseEntity createVacancy(@RequestBody VacancyRequest request)
    {
        return ResponseEntity.ok(vacancyService.createVacancy(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteVacancy(@PathVariable Long id)
    {
        Response response=vacancyService.deleteVacancy(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("requirement/{id}")
    public ResponseEntity deleterequirement(@PathVariable Long id)
    {
        requirementRepository.deleteById(id);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateVacancy(@PathVariable Long id, @RequestBody VacancyRequest request)
    {
        Response response=vacancyService.updateVacancy(id, request);
        return ResponseEntity.ok(response);
    }
}
