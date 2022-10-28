package com.example.postgresql.repository;

import com.example.postgresql.model.VacancyLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyLanguageRepository extends JpaRepository<VacancyLanguage, Long> {
}
