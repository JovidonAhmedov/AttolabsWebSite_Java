package com.example.postgresql.service;

import com.example.postgresql.dto.response.Response;
import com.example.postgresql.model.Project;
import com.example.postgresql.model.VacancyGroup;
import com.example.postgresql.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public List<Project> getProjects()
    {
        return projectRepository.findAll();
    }

    public Project getProject(long id)
    {
        if(projectRepository.existsById(id)) {
            return projectRepository.findById(id).get();
        }
        return null;
    }

    public Response createProject(Project project)
    {
        Response response=null;
        try {
            projectRepository.save(project);
            response=new Response("Новая группа ваканции успешно создана.",200);
        }
        catch (Exception ex)
        {
            response=new Response("Ошибка во время создание.",-1);
        }
        return response;
    }

    public Response deleteProject(long id)
    {
        if(projectRepository.existsById(id))
        {
            try {
                projectRepository.deleteById(id);
                return new Response("Успешно удалено",1);
            }
            catch(Exception ex)
            {
                return new Response("Ошибка во время удаление.",-1);
            }
        }
        return new Response("Группа ваканций не найдена.",0);
    }

    public Response updateProject(long id, Project newProject)
    {
        if(projectRepository.existsById(id)) {
            try {
                Project project = projectRepository.findById(id).get();
                if(newProject.getName()!=null) {
                    project.setName(newProject.getName());
                }
                projectRepository.save(project);
                return new Response("Успешно изменена",1);
            }
            catch (Exception ex){
                return new Response("Ошибка во время изменение.",-1);
            }
        }
        return new Response("Группа ваканций не найдена.",0);
    }
}
