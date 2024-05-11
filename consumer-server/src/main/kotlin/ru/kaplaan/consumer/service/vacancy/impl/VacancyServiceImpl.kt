package ru.kaplaan.consumer.service.vacancy.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.domain.exception.PermissionDeniedException
import ru.kaplaan.consumer.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.consumer.repository.consumer.vacancy.VacancyRepository
import ru.kaplaan.consumer.service.payment.PaymentInfoService
import ru.kaplaan.consumer.service.vacancy.VacancyService

@Service
class VacancyServiceImpl(
    private val vacancyRepository: VacancyRepository,
    private val paymentInfoService: PaymentInfoService
): VacancyService {

    @Value("\${page-size.vacancy}")
    val pageSize: Int? = null

    @Transactional
    override fun save(vacancy: Vacancy): Vacancy{
        if(!paymentInfoService.existsByCompanyId(vacancy.companyId!!))
            throw PermissionDeniedException("Чтобы добавить вакансию, добавьте платежную информацию о компании!")

        return vacancyRepository.save(vacancy)
    }

    @Transactional
    override fun update(vacancy: Vacancy): Vacancy{
        if(!vacancyRepository.existsById(vacancy.id!!))
            throw VacancyNotFoundException()

        return vacancyRepository.save(vacancy)
    }

    @Transactional
    override fun delete(companyId: Long, vacancyId: Long) =
            vacancyRepository.deleteByCompanyIdAndVacancyId(companyId, vacancyId)

    @Transactional
    override fun getVacancyById(vacancyId: Long): Vacancy =
        vacancyRepository.findVacancyById(vacancyId)
            ?: throw VacancyNotFoundException()

    @Transactional
    override fun getVacanciesByCompanyId(companyId: Long, pageNumber: Int): List<Vacancy> =
        vacancyRepository.findAllByCompanyId(companyId, PageRequest.of(pageNumber, pageSize!!))

    @Transactional
    override fun getVacancies(pageNumber: Int): List<Vacancy> =
        vacancyRepository.findAllVacancies(PageRequest.of(pageNumber, pageSize!!))

    @Transactional
    override fun getVacanciesByText(text: String, pageNumber: Int): List<Vacancy> =
        vacancyRepository.findAllByVacanciesByText(text, PageRequest.of(pageNumber, pageSize!!))

    @Transactional
    override fun existsVacancyByVacancyIdAndCompanyId(vacancyId: Long, companyId: Long): Boolean =
        vacancyRepository.existVacancyByVacancyIdAndCompanyId(vacancyId, companyId)

    @Transactional
    override fun archiveVacancy(companyId: Long, vacancyId: Long) =
            vacancyRepository.archiveVacancyByCompanyIdAndVacancyId(companyId, vacancyId)

    @Transactional
    override fun unarchiveVacancy(companyId: Long, vacancyId: Long) =
        vacancyRepository.unarchiveVacancyByCompanyIdAndVacancyId(companyId, vacancyId)
}