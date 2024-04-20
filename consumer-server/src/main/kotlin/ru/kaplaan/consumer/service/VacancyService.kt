package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy

@Service
interface VacancyService {

    fun save(vacancy: Vacancy): Vacancy

    fun update(vacancy: Vacancy): Vacancy

    fun delete(companyId: Long, vacancyId: Long)

    fun getVacancyById(vacancyId: Long): Vacancy

    fun getVacanciesByCompanyId(companyId: Long, pageNumber: Int): List<Vacancy>

    fun getVacancies(pageNumber: Int): List<Vacancy>

    fun getVacanciesByText(text: String, pageNumber: Int): List<Vacancy>

    fun archiveVacancy(companyId: Long, vacancyId: Long)

    fun unarchiveVacancy(companyId: Long, vacancyId: Long)
}