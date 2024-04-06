package ru.kaplaan.vacancy.service

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.vacancy.Vacancy

@Service
interface VacancyService {

    fun save(vacancy: Vacancy): Vacancy

    fun update(vacancy: Vacancy): Vacancy

    fun delete(vacancyId: Long)

    fun getVacancyById(vacancyId: Long): Vacancy

    fun getVacanciesByCompanyId(companyId: Long, pageNumber: Int): List<Vacancy>
}