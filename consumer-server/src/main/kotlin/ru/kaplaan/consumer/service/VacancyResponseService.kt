package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse

@Service
interface VacancyResponseService {


    fun save(vacancyResponse: VacancyResponse): VacancyResponse

    fun update(vacancyResponse: VacancyResponse): VacancyResponse

    fun delete(vacancyId: Long, userId: Long)

    fun getAllUserIdByVacancyIdAndCompanyId(vacancyId: Long, companyId: Long, pageNumber: Int): List<Long>
}