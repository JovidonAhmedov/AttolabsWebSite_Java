package com.example.postgresql.Utils;

import com.example.postgresql.model.City;
import com.example.postgresql.model.Country;
import com.example.postgresql.model.Language;
import com.example.postgresql.model.VacancyGroup;
import com.example.postgresql.repository.CityRepository;
import com.example.postgresql.repository.CountryRepository;
import com.example.postgresql.repository.LanguageRepository;
import com.example.postgresql.repository.VacancyGroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Initializer implements CommandLineRunner {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final LanguageRepository languageRepository;
    private final VacancyGroupRepository vacancyGroupRepository;

    public Initializer(CountryRepository countryRepository, CityRepository cityRepository, LanguageRepository languageRepository, VacancyGroupRepository vacancyGroupRepository) {
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.languageRepository = languageRepository;
        this.vacancyGroupRepository = vacancyGroupRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(countryRepository.count()<1){
            List<Country> countries=new ArrayList<>();

            Country country=new Country();
            country.setName("Tajikistan");
            countries.add(country);

            country=new Country();
            country.setName("Uzbekistan");
            countries.add(country);

            country=new Country();
            country.setName("Germany");
            countries.add(country);

            countryRepository.saveAll(countries);
        }

        if(cityRepository.count()<1){
            List<City> cities=new ArrayList<>();
            City city =new City();
            city.setName("Dushanbe");
            city.setCountry(countryRepository.findById((long)1).get());
            cities.add(city);

            city =new City();
            city.setName("Khujand");
            city.setCountry(countryRepository.findById((long)1).get());
            cities.add(city);

            city =new City();
            city.setName("Tashkent");
            city.setCountry(countryRepository.findById((long)2).get());
            cities.add(city);

            city =new City();
            city.setName("Samarkand");
            city.setCountry(countryRepository.findById((long)2).get());
            cities.add(city);

            city =new City();
            city.setName("Keln");
            city.setCountry(countryRepository.findById((long)3).get());
            cities.add(city);

            city =new City();
            city.setName("Berlin");
            city.setCountry(countryRepository.findById((long)3).get());
            cities.add(city);

            cityRepository.saveAll(cities);
        }

        if(languageRepository.count()<1){
            List<Language> languages=new ArrayList<>();

            Language language=new Language();
            language.setName("Русский");
            languages.add(language);

            language=new Language();
            language.setName("Английский");
            languages.add(language);

            language=new Language();
            language.setName("Немецкий");
            languages.add(language);

            languageRepository.saveAll(languages);
        }

        if(vacancyGroupRepository.count()<1){
            List<VacancyGroup> vacancyGroups=new ArrayList<>();

            VacancyGroup vacancyGroup=new VacancyGroup();
            vacancyGroup.setName("Разработка");
            vacancyGroups.add(vacancyGroup);

            vacancyGroup=new VacancyGroup();
            vacancyGroup.setName("Аналитика");
            vacancyGroups.add(vacancyGroup);

            vacancyGroup=new VacancyGroup();
            vacancyGroup.setName("Поддержка");
            vacancyGroups.add(vacancyGroup);

            vacancyGroupRepository.saveAll(vacancyGroups);
        }
    }
}
