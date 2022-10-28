package com.example.postgresql.repository;

import com.example.postgresql.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy,Long> {
}
