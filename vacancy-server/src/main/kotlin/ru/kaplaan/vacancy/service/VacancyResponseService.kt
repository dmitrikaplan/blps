package ru.kaplaan.vacancy.service

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.VacancyResponse

@Service
interface VacancyResponseService {


    fun save(vacancyResponse: VacancyResponse): VacancyResponse

    fun delete(vacancyResponseId: Long)

    fun getAllUsernameByVacancyId(companyId: Long, pageNumber: Int): List<String>
}