package com.example.postgresql.controller;

import com.example.postgresql.dto.response.Response;
import com.example.postgresql.model.City;
import com.example.postgresql.model.Project;
import com.example.postgresql.repository.ProjectRepository;
import com.example.postgresql.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")

public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity getProjects(){
        return ResponseEntity.ok(this.projectService.getProjects());
    }
    @GetMapping("/{id}")
    public ResponseEntity getProject(@PathVariable long id){
        return ResponseEntity.ok(projectService.getProject(id));
    }

    @PostMapping
    public ResponseEntity createProject(@RequestBody Project project){
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable long id){
        Response result = projectService.deleteProject(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable long id, @RequestBody Project project){
        Response result=projectService.updateProject(id, project);
        if(result!=null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok("Проект с id:"+id+" не существует");
    }
}
