package ru.kaplaan.consumer.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.consumer.repository.VacancyRepository
import ru.kaplaan.consumer.service.VacancyService

@Service
class VacancyServiceImpl(
    private val vacancyRepository: VacancyRepository,
): VacancyService {

    @Value("\${vacancy.page-size}")
    val pageSize: Int? = null

    override fun save(vacancy: Vacancy): Vacancy =
        vacancyRepository.save(vacancy)

    override fun update(vacancy: Vacancy): Vacancy =
        vacancyRepository.save(vacancy)

    override fun delete(companyId: Long, vacancyId: Long) =
            vacancyRepository.deleteByCompanyIdAndVacancyId(companyId, vacancyId)

    override fun getVacancyById(vacancyId: Long): Vacancy =
        vacancyRepository.findVacancyById(vacancyId)
            ?: throw VacancyNotFoundException()


    override fun getVacanciesByCompanyId(companyId: Long, pageNumber: Int): List<Vacancy> =
        vacancyRepository.findAllByCompanyId(companyId, PageRequest.of(pageNumber, pageSize!!))


    override fun getVacancies(pageNumber: Int): List<Vacancy> =
        vacancyRepository.findAllVacancies(PageRequest.of(pageNumber, pageSize!!))

    override fun getVacanciesByText(text: String, pageNumber: Int): List<Vacancy> =
        vacancyRepository.findAllByVacanciesByText(text, PageRequest.of(pageNumber, pageSize!!))


    override fun archiveVacancy(companyId: Long, vacancyId: Long) =
            vacancyRepository.archiveVacancyByCompanyIdAndVacancyId(companyId, vacancyId)

    override fun unarchiveVacancy(companyId: Long, vacancyId: Long) =
        vacancyRepository.unarchiveVacancyByCompanyIdAndVacancyId(companyId, vacancyId)
}