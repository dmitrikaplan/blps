package ru.kaplaan.consumer.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.consumer.repository.VacancyRepository
import ru.kaplaan.consumer.service.VacancyService

@Service
class VacancyServiceImpl(
    private val vacancyRepository: VacancyRepository
): VacancyService {

    @Value("\${vacancy.page-size}")
    val pageSize: Int? = null

    override fun save(vacancy: Vacancy): Vacancy =
        vacancyRepository.save(vacancy)

    override fun update(vacancy: Vacancy): Vacancy =
        vacancyRepository.save(vacancy)

    override fun delete(companyName: String, vacancyId: Long){
        val companyId = 1L //TODO: добавить rabbitMQ
        vacancyRepository.deleteByCompanyIdAndVacancyId(companyId, vacancyId)
    }
    override fun getVacancyById(vacancyId: Long): Vacancy =
        vacancyRepository.findByIdOrNull(vacancyId)
            ?: throw VacancyNotFoundException()

    override fun getVacanciesByCompanyName(companyName: String, pageNumber: Int): List<Vacancy>{
        val companyId = 1L //TODO: добавить rabbitMq
        return vacancyRepository.findAllByCompanyId(companyId, PageRequest.of(pageNumber, pageSize!!))
    }
}