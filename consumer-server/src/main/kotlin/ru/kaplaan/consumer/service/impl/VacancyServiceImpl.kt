package ru.kaplaan.consumer.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.consumer.repository.VacancyRepository
import ru.kaplaan.consumer.service.UserServiceInfo
import ru.kaplaan.consumer.service.VacancyService

@Service
class VacancyServiceImpl(
    private val vacancyRepository: VacancyRepository,
    private val userServiceInfo: UserServiceInfo
): VacancyService {

    @Value("\${vacancy.page-size}")
    val pageSize: Int? = null

    override fun save(vacancy: Vacancy): Vacancy =
        vacancyRepository.save(vacancy)

    override fun update(vacancy: Vacancy): Vacancy =
        vacancyRepository.save(vacancy)

    override fun delete(companyName: String, vacancyId: Long) =
        userServiceInfo.getUserIdByUsername(companyName).let { companyId ->
            vacancyRepository.deleteByCompanyIdAndVacancyId(companyId, vacancyId)
        }

    override fun getVacancyById(vacancyId: Long): Vacancy =
        vacancyRepository.findByIdOrNull(vacancyId)?.apply {
            this.companyName = userServiceInfo.getUsernameByUserId(this.companyId!!)
        } ?: throw VacancyNotFoundException()

    override fun getVacanciesByCompanyName(companyName: String, pageNumber: Int): List<Vacancy> =
        userServiceInfo.getUserIdByUsername(companyName).let { companyId ->

            vacancyRepository.findAllByCompanyId(companyId, PageRequest.of(pageNumber, pageSize!!)).also { vacancies ->
                val usernames = userServiceInfo.getAllUsernamesByUserIds(vacancies.map { it.vacancyId!!})

                vacancies.forEachIndexed{index, vacancy ->
                    vacancy.companyName = usernames[index]
                }
            }

        }

    override fun archiveVacancy(companyName: String, vacancyId: Long) {
        userServiceInfo.getUserIdByUsername(companyName).let { companyId ->
            vacancyRepository.archiveVacancyByCompanyIdAndVacancyId(companyId, vacancyId)
        }
    }
}