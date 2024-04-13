package ru.kaplaan.vacancy.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.VacancyResponse
import ru.kaplaan.vacancy.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.vacancy.repository.VacancyRepository
import ru.kaplaan.vacancy.repository.VacancyResponseRepository
import ru.kaplaan.vacancy.service.UserService
import ru.kaplaan.vacancy.service.VacancyResponseService

@Service
class VacancyResponseServiceImpl(
    private val vacancyResponseRepository: VacancyResponseRepository,
    private val vacancyRepository: VacancyRepository,
    private val userService: UserService
): VacancyResponseService {

    @Value("\${vacancy-response.page-size}")
    var pageSize: Int? = null

    override fun save(vacancyResponse: VacancyResponse): VacancyResponse =
        vacancyRepository.findVacancyByVacancyId(vacancyResponse.vacancyId)?.let{
            vacancyResponseRepository.save(
                vacancyResponse.apply {
                    this.userId = userService.getUserIdByUsername(vacancyResponse.username)
                }
            )
        } ?: throw VacancyNotFoundException()


    override fun delete(vacancyResponseId: Long) =
        vacancyResponseRepository.deleteById(vacancyResponseId)

    override fun getAllUsernamesByCompanyName(companyName: String, pageNumber: Int): List<String>{
      return vacancyRepository.findVacancyIdByCompanyName(companyName).let { vacancyId ->
            vacancyResponseRepository.findAllUserIdByVacancyId(vacancyId, PageRequest.of(pageNumber, pageSize!!))
        }.let {
            userService.getAllUsernamesByUserIds(it)
       }
    }

}