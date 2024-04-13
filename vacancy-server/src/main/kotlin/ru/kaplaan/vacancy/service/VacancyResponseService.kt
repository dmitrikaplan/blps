package ru.kaplaan.vacancy.service

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.VacancyResponse

@Service
interface VacancyResponseService {


    fun save(vacancyResponse: VacancyResponse): VacancyResponse

    fun delete(vacancyResponseId: Long)

    fun getAllUsernamesByCompanyName(companyName: String, pageNumber: Int): List<String>
}