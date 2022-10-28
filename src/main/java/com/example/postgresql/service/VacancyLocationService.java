package com.example.postgresql.service;

import com.example.postgresql.dto.request.CityRequest;
import com.example.postgresql.dto.request.VacancyCityRequest1;
import com.example.postgresql.dto.response.Response;
import com.example.postgresql.mapper.CityMapper;
import com.example.postgresql.model.*;
import com.example.postgresql.repository.CityRepository;
import com.example.postgresql.repository.CountryRepository;
import com.example.postgresql.repository.VacancyCityRepository;
import com.example.postgresql.repository.VacancyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyLocationService {
    private final CityRepository cityRepository;
    private final VacancyRepository vacancyRepository;
    private final VacancyCityRepository vacancyCityRepository;
    private final CountryRepository countryRepository;

    public VacancyLocationService(CityRepository cityRepository, VacancyRepository vacancyRepository, VacancyCityRepository vacancyCityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.vacancyRepository = vacancyRepository;
        this.vacancyCityRepository = vacancyCityRepository;
        this.countryRepository = countryRepository;
    }

    public List<City> getCities() {
        return cityRepository.findAll();
    }

    public City getCity(long id) {
        if (cityRepository.existsById(id)) {
            return cityRepository.findById(id).get();
        }
        return null;
    }

    public Response createCity(CityRequest request) {
        Response response;
        try {
            City city = CityMapper.ToCity(request);
            city = cityRepository.save(city);
            response = new Response("Новый город успешно создан.", 200);
            if (request.getCountry_id() > 0)
                response = assignCountryToCity(city.getId(), request.getCountry_id());
        } catch (Exception ex) {
            response = new Response("Ошибка во время создание", -1);
        }
        return response;
    }

    public Response deleteCity(long id) {
        if (cityRepository.existsById(id)) {
            try {
                cityRepository.deleteById(id);
                return new Response("Успешно удалено", 1);
            } catch (Exception ex) {
                return new Response("Ошибка во время удаление.", -1);
            }
        }
        return new Response("Город не найден.", 0);
    }

    public Response updateCity(long id, CityRequest newCity) {
        if (cityRepository.existsById(id)) {
            try {
                City city = cityRepository.findById(id).get();
                if (newCity.getName() != null) {
                    city.setName(newCity.getName());
                }

                if (newCity.getCountry_id() > 0 && countryRepository.existsById(newCity.getCountry_id())) {
                    Country country = countryRepository.findById(newCity.getCountry_id()).get();
                    city.setCountry(country);
                }

                cityRepository.save(city);

                return new Response("Успешно изменен", 1);
            } catch (Exception ex) {
                return new Response("Ошибка во время изменение.", -1);
            }
        }
        return new Response("Город не найден.", 0);
    }

    public List<Country> getCountries() {
        return countryRepository.findAll();
    }

    public Country getCountry(long id) {
        if (countryRepository.existsById(id)) {
            return countryRepository.findById(id).get();
        }
        return null;
    }

    public Response createCountry(Country country) {
        Response response = null;
        try {
            countryRepository.save(country);
            response = new Response("Новая страна успешно создана.", 200);
        } catch (Exception ex) {
            response = new Response("Ошибка во время создание.", -1);
        }
        return response;
    }

    public Response deleteCountry(long id) {
        if (countryRepository.existsById(id)) {
            try {
                countryRepository.deleteById(id);
                return new Response("Успешно удалено", 1);
            } catch (Exception ex) {
                return new Response("Ошибка во время удаление.", -1);
            }
        }
        return new Response("Страна не найдена.", 0);
    }

    public Response updateCountry(long id, Country newCountry) {
        if (countryRepository.existsById(id)) {
            try {
                Country country = countryRepository.findById(id).get();
                if (newCountry.getName() != null) {
                    country.setName(newCountry.getName());
                }
                countryRepository.save(country);
                return new Response("Успешно изменен", 1);
            } catch (Exception ex) {
                return new Response("Ошибка во время изменение.", -1);
            }
        }
        return new Response("Страна не найдена.", 0);
    }

    public Response assignCountryToCity(long cityId, long countryId) {
        Response response;
        City city = null;
        if (cityRepository.existsById(cityId)) {
            city = cityRepository.findById(cityId).get();
        }
        Country country = null;
        if (countryRepository.existsById(countryId))
            country = countryRepository.findById(countryId).get();
        if (city != null) {
            if (country != null) {
                city.assignCountry(country);
                cityRepository.save(city);
                response = new Response("Успешно изменен", 200);
            } else {
                response = new Response("Ошибка. Страна не найдена!", 0);
            }
        } else {
            response = new Response("Error. City not found!", 0);
        }

        return response;
    }

    public List<VacancyCity> getVacancyCities() {
        return vacancyCityRepository.findAll();
    }

    public VacancyCity getVacancyCity(long id) {
        if (vacancyCityRepository.existsById(id)) {
            return vacancyCityRepository.findById(id).get();
        }
        return null;
    }

    public Response createVacancyCity(VacancyCityRequest1 vacancyCityRequest1)
    {
        Response response = null;
        try {
            if (vacancyCityRequest1.getVacancy_id() > 0 && vacancyCityRequest1.getCity_id() > 0)
            {
                if (vacancyRepository.existsById(vacancyCityRequest1.getVacancy_id()) && cityRepository.existsById(vacancyCityRequest1.getCity_id()))
                {
                    long city_id= vacancyCityRequest1.getCity_id();
                    City city = cityRepository.findById(city_id).get();
                    Vacancy vacancy = vacancyRepository.findById(vacancyCityRequest1.getVacancy_id()).get();
                    VacancyCity vacancyCity=new VacancyCity();
                    vacancyCity.assignVacancyToVacancyCity(vacancy);
                    vacancyCity.assignCityToVacancyCity(city);
                    vacancyCity.setVacancy_status(vacancyCityRequest1.isStatus());
                    vacancyCityRepository.save(vacancyCity);
                    response = new Response("Новый город ваканций успешно создан.", 1);
                } else {
                    response = new Response("Город и/или ваканции не найдены", 0);
                }
            } else {
                response = new Response("Не верный формат город и/или ваканции", 0);
            }
        } catch (Exception ex) {
            response = new Response("Ошибка во время создание.", -1);
        }
        return response;
    }

    public Response deleteVacancyCity(long id) {
        if (vacancyCityRepository.existsById(id)) {
            try {
                vacancyCityRepository.deleteById(id);
                return new Response("Успешно удалено", 1);
            } catch (Exception ex) {
                return new Response("Ошибка во время удаление.", -1);
            }
        }
        return new Response("Город ваканций не найден.", 0);
    }

    public Response updateVacancyCity(long id, VacancyCityRequest1 vacancyCityRequest1) {
        Response response = null;
        try {
            if(vacancyCityRepository.existsById(id)) {
                VacancyCity vacancyCity=vacancyCityRepository.findById(id).get();
                if (vacancyCityRequest1.getVacancy_id() > 0 && vacancyCityRequest1.getCity_id() > 0) {
                    if (vacancyRepository.existsById(vacancyCityRequest1.getVacancy_id()) && cityRepository.existsById(vacancyCityRequest1.getCity_id()))
                    {
                        City city = cityRepository.findById(vacancyCityRequest1.getCity_id()).get();
                        Vacancy vacancy = vacancyRepository.findById(vacancyCityRequest1.getVacancy_id()).get();

                        vacancyCity.assignVacancyToVacancyCity(vacancy);
                        vacancyCity.assignCityToVacancyCity(city);
                        vacancyCity.setVacancy_status(vacancyCityRequest1.isStatus());
                        vacancyCityRepository.save(vacancyCity);
                        response = new Response("Город ваканций успешно изменен.", 1);
                    }
                    else
                    {
                        response = new Response("Город и/или ваканции не найдены", 0);
                    }
                } else {
                    response = new Response("Не верный формат город и/или ваканции", 0);
                }
            }
        } catch (Exception ex) {
            response = new Response("Ошибка во время создание.", -1);
        }
        return response;
    }

}
