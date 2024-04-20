package ru.kaplaan.consumer.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import ru.kaplaan.consumer.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.consumer.repository.VacancyRepository
import ru.kaplaan.consumer.repository.VacancyResponseRepository
import ru.kaplaan.consumer.service.VacancyResponseService

@Service
class VacancyResponseServiceImpl(
    private val vacancyResponseRepository: VacancyResponseRepository,
    private val vacancyRepository: VacancyRepository,
) : VacancyResponseService {

    @Value("\${vacancy-response.page-size}")
    var pageSize: Int? = null

    override fun save(vacancyResponse: VacancyResponse): VacancyResponse {
        return vacancyRepository.findVacancyByVacancyIdAndNotIsArchived(vacancyResponse.pk.vacancyId)?.let {
            vacancyResponseRepository.save(vacancyResponse)
        } ?: throw VacancyNotFoundException()
    }


    override fun delete(vacancyId: Long, userId: Long) =
        vacancyResponseRepository.deleteById(VacancyResponse.PK(vacancyId, userId))


    override fun getAllUserIdByVacancyIdAndCompanyId(
        vacancyId: Long,
        companyId: Long,
        pageNumber: Int,
    ): List<Long> {
        return vacancyResponseRepository.findAllUserIdByVacancyIdAndCompanyId(vacancyId, companyId, PageRequest.of(pageNumber, pageSize!!))
    }

}