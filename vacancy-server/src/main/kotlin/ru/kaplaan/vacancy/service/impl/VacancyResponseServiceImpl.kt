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

    override fun save(vacancyResponse: VacancyResponse): VacancyResponse{
        vacancyResponse.apply {
            pk = VacancyResponse.PK(
                vacancyId,
                userService.getUserIdByUsername(vacancyResponse.username)
            )
        }
        return vacancyRepository.findVacancyByVacancyId(vacancyResponse.pk.vacancyId)?.let{
            vacancyResponseRepository.save(vacancyResponse)
        } ?: throw VacancyNotFoundException()
    }


    override fun delete(vacancyId: Long, username: String) =
        userService.getUserIdByUsername(username).let { userId ->
            vacancyResponseRepository.deleteById(VacancyResponse.PK(vacancyId, userId))
        }


    override fun getAllUsernamesByCompanyName(companyName: String, pageNumber: Int): List<String>{
        val companyId = userService.getUserIdByUsername(companyName)
      return vacancyRepository.findVacancyIdByCompanyId(companyId).let { vacancyId ->
            vacancyResponseRepository.findAllUsernameByVacancyId(vacancyId, PageRequest.of(pageNumber, pageSize!!))
        }
    }

}