package ru.kaplaan.consumer.service.vacancy

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse

@Service
interface VacancyResponseService {


    fun save(vacancyResponse: VacancyResponse): VacancyResponse

    fun update(vacancyResponse: VacancyResponse): VacancyResponse

    fun delete(vacancyId: Long, userId: Long)

    fun getVacancyResponseById(companyId: Long, pk: VacancyResponse.PK): VacancyResponse

    fun getAllUserIdByVacancyIdAndCompanyId(vacancyId: Long, companyId: Long, pageNumber: Int): List<Long>
}