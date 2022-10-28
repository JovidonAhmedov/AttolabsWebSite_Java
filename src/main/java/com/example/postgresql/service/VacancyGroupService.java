package com.example.postgresql.service;

import com.example.postgresql.dto.response.Response;
import com.example.postgresql.model.City;
import com.example.postgresql.model.Country;
import com.example.postgresql.model.VacancyGroup;
import com.example.postgresql.repository.VacancyGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyGroupService {
    private final VacancyGroupRepository vacancyGroupRepository;

    public VacancyGroupService(VacancyGroupRepository vacancyGroupRepository) {
        this.vacancyGroupRepository = vacancyGroupRepository;
    }

    public List<VacancyGroup> getVacancyGroups()
    {
        return vacancyGroupRepository.findAll();
    }

    public VacancyGroup getVacancyGroup(long id)
    {
        if(vacancyGroupRepository.existsById(id)) {
            return vacancyGroupRepository.findById(id).get();
        }
        return null;
    }

    public Response createVacancyGroup(VacancyGroup vacancyGroup)
    {
        Response response=null;
        try {
            vacancyGroupRepository.save(vacancyGroup);
            response=new Response("Новая группа ваканции успешно создана.",200);
        }
        catch (Exception ex)
        {
            response=new Response("Ошибка во время создание.",-1);
        }
        return response;
    }

    public Response deleteVacancyGroup(long id)
    {
        if(vacancyGroupRepository.existsById(id))
        {
            try {
                vacancyGroupRepository.deleteById(id);
                return new Response("Успешно удалено",1);
            }
            catch(Exception ex)
            {
                return new Response("Ошибка во время удаление.",-1);
            }
        }
        return new Response("Группа ваканций не найдена.",0);
    }

    public Response updateVacancyGroup(long id, VacancyGroup newVacancyGroup)
    {
        if(vacancyGroupRepository.existsById(id)) {
            try {
                VacancyGroup vacancyGroup = vacancyGroupRepository.findById(id).get();
                if(newVacancyGroup.getName()!=null) {
                    vacancyGroup.setName(newVacancyGroup.getName());
                }
                vacancyGroupRepository.save(vacancyGroup);
                return new Response("Успешно изменена",1);
            }
            catch (Exception ex){
                return new Response("Ошибка во время изменение.",-1);
            }
        }
        return new Response("Группа ваканций не найдена.",0);
    }

}
