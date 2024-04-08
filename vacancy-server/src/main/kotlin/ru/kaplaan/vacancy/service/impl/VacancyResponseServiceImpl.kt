package ru.kaplaan.vacancy.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.VacancyResponse
import ru.kaplaan.vacancy.repository.VacancyRepository
import ru.kaplaan.vacancy.repository.VacancyResponseRepository
import ru.kaplaan.vacancy.service.VacancyResponseService

@Service
class VacancyResponseServiceImpl(
    private val vacancyResponseRepository: VacancyResponseRepository,
    private val vacancyRepository: VacancyRepository
): VacancyResponseService {

    @Value("\${vacancy-response.page-size}")
    var pageSize: Int? = null

    override fun save(vacancyResponse: VacancyResponse): VacancyResponse =
        vacancyResponseRepository.save(vacancyResponse)

    override fun delete(vacancyResponseId: Long) =
        vacancyResponseRepository.deleteById(vacancyResponseId)

    override fun getAllUsernameByVacancyId(companyId: Long, pageNumber: Int): List<String>{
       val ids =  vacancyRepository.findVacancyIdByCompanyId(companyId).let { vacancyId ->
            vacancyResponseRepository.findAllUserIdByVacancyId(vacancyId, PageRequest.of(pageNumber, pageSize!!))
        }
        //TODO: добавить rabbitMQ
        return listOf()
    }

}