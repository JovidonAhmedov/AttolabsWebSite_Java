package com.example.postgresql.mapper;

import com.example.postgresql.dto.request.RequirementRequest;
import com.example.postgresql.dto.response.RequirementResponse;
import com.example.postgresql.model.Requirement;
import com.example.postgresql.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class RequirementMapper {
    public static RequirementResponse ToRequirementResponse(Requirement requirement)
    {
        RequirementResponse response=new RequirementResponse();
        response.setTitle(requirement.getTitle());
        return response;
    }

    public static List<RequirementResponse> ToRequirementResponses(List<Requirement> requirements)
    {
        List<RequirementResponse> responses=new ArrayList<>();

        for (Requirement requirement: requirements) {
            responses.add(ToRequirementResponse(requirement));
        }
        return responses;
    }

    public static List<Requirement> ToRequirements(List<RequirementRequest> requests, Vacancy vacancy) {
        List<Requirement> requirements=new ArrayList<>();
        for (RequirementRequest request:requests) {
            requirements.add(ToRequirement(request, vacancy));
        }
        return requirements;
    }

    public static Requirement ToRequirement(RequirementRequest request , Vacancy vacancy) {
       Requirement requirement=new Requirement();
       requirement.setTitle(request.getTitle());
       requirement.setVacancy(vacancy);
       return requirement;
    }
}
