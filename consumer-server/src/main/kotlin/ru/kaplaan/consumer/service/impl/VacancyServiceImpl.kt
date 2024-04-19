package ru.kaplaan.consumer.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.consumer.repository.VacancyRepository
import ru.kaplaan.consumer.service.UserInfoService
import ru.kaplaan.consumer.service.VacancyService

@Service
class VacancyServiceImpl(
    private val vacancyRepository: VacancyRepository,
    private val userInfoService: UserInfoService
): VacancyService {

    @Value("\${vacancy.page-size}")
    val pageSize: Int? = null

    override fun save(vacancy: Vacancy): Vacancy =
        vacancyRepository.save(vacancy)

    override fun update(vacancy: Vacancy): Vacancy =
        vacancyRepository.save(vacancy)

    override fun delete(companyName: String, vacancyId: Long) =
        userInfoService.getUserIdByUsername(companyName).let { companyId ->
            vacancyRepository.deleteByCompanyIdAndVacancyId(companyId, vacancyId)
        }

    override fun getVacancyById(vacancyId: Long): Vacancy =
        vacancyRepository.findByIdOrNull(vacancyId)?.apply {
            this.companyName = userInfoService.getUsernameByUserId(this.companyId!!)
        } ?: throw VacancyNotFoundException()

    override fun getVacanciesByCompanyName(companyName: String, pageNumber: Int): List<Vacancy> =
        userInfoService.getUserIdByUsername(companyName).let { companyId ->

            vacancyRepository.findAllByCompanyId(companyId, PageRequest.of(pageNumber, pageSize!!)).also { vacancies ->
                val usernames = userInfoService.getAllUsernamesByUserIds(vacancies.map { it.id!!})

                vacancies.forEachIndexed{index, vacancy ->
                    vacancy.companyName = usernames[index]
                }
            }

        }

    override fun getVacancies(pageNumber: Int): List<Vacancy> {
        return vacancyRepository.findAllVacancies(PageRequest.of(pageNumber, pageSize!!))
    }

    override fun getVacanciesByText(text: String, pageNumber: Int): List<Vacancy> {
        return vacancyRepository.findAllByVacanciesByText(text, PageRequest.of(pageNumber, pageSize!!))
    }

    override fun archiveVacancy(companyName: String, vacancyId: Long) {
        userInfoService.getUserIdByUsername(companyName).let { companyId ->
            vacancyRepository.archiveVacancyByCompanyIdAndVacancyId(companyId, vacancyId)
        }
    }

    override fun unarchiveVacancy(companyName: String, vacancyId: Long) {
        userInfoService.getUserIdByUsername(companyName).let { companyId ->
            vacancyRepository.unarchiveVacancyByCompanyIdAndVacancyId(companyId, vacancyId)
        }
    }
}