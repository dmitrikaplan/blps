package ru.kaplaan.vacancy.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.vacancy.Vacancy
import ru.kaplaan.vacancy.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.vacancy.repository.VacancyRepository
import ru.kaplaan.vacancy.service.VacancyService

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

    override fun delete(vacancyId: Long) =
        vacancyRepository.deleteById(vacancyId)

    override fun getVacancyById(vacancyId: Long): Vacancy =
        vacancyRepository.findByIdOrNull(vacancyId)
            ?: throw VacancyNotFoundException()

    override fun getVacanciesByCompanyId(companyId: Long, pageNumber: Int): List<Vacancy>  =
        vacancyRepository.findAllByCompanyId(companyId, PageRequest.of(pageNumber, pageSize!!))
}