package ru.kaplaan.consumer.service.vacancy

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import java.time.LocalDate

@Service
interface VacancyResponseService {


    fun save(vacancyResponse: VacancyResponse): VacancyResponse

    fun update(vacancyResponse: VacancyResponse, companyId: Long): VacancyResponse

    fun delete(vacancyId: Long, userId: Long)

    fun getVacancyResponseById(pk: VacancyResponse.PK): VacancyResponse

    fun getAllVacancyResponsesByUserId(userId: Long): List<VacancyResponse>

    fun getVacancyResponseByIdAndCompanyId(companyId: Long, pk: VacancyResponse.PK): VacancyResponse

    fun getAllUserIdByVacancyIdAndCompanyId(vacancyId: Long, companyId: Long, pageNumber: Int): List<Long>

    fun getAllVacancyIdByDateLastStatusUpdateBetweenAndAccepted(startDate: LocalDate, finishDate: LocalDate): List<Long>
    fun countVacancyResponsesByDateBetweenAndVacancyIdAndAccepted(
        startDate: LocalDate,
        finishDate: LocalDate,
        vacancyId: Long
    ): Long
}