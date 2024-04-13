package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy

@Service
interface VacancyService {

    fun save(vacancy: Vacancy): Vacancy

    fun update(vacancy: Vacancy): Vacancy

    fun delete(companyName: String, vacancyId: Long)

    fun getVacancyById(vacancyId: Long): Vacancy

    fun getVacanciesByCompanyName(companyName: String, pageNumber: Int): List<Vacancy>
}