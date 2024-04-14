package ru.kaplaan.consumer.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancyResponse.VacancyResponse
import ru.kaplaan.consumer.domain.exception.notFound.VacancyNotFoundException
import ru.kaplaan.consumer.repository.VacancyRepository
import ru.kaplaan.consumer.repository.VacancyResponseRepository
import ru.kaplaan.consumer.service.UserServiceInfo
import ru.kaplaan.consumer.service.VacancyResponseService

@Service
class VacancyResponseServiceImpl(
    private val vacancyResponseRepository: VacancyResponseRepository,
    private val vacancyRepository: VacancyRepository,
    private val userServiceInfo: UserServiceInfo
): VacancyResponseService {

    @Value("\${vacancy-response.page-size}")
    var pageSize: Int? = null

    override fun save(vacancyResponse: VacancyResponse): VacancyResponse {
        vacancyResponse.apply {
            pk = VacancyResponse.PK(
                vacancyId,
                userServiceInfo.getUserIdByUsername(vacancyResponse.username)
            )
        }
        return vacancyRepository.findVacancyByVacancyIdAndNotIsArchived(vacancyResponse.pk.vacancyId)?.let{
            vacancyResponseRepository.save(vacancyResponse)
        } ?: throw VacancyNotFoundException()
    }


    override fun delete(vacancyId: Long, username: String) =
        userServiceInfo.getUserIdByUsername(username).let { userId ->
            vacancyResponseRepository.deleteById(VacancyResponse.PK(vacancyId, userId))
        }


    override fun getAllUsernamesByCompanyName(companyName: String, pageNumber: Int): List<String>{
        val companyId = userServiceInfo.getUserIdByUsername(companyName)
      return vacancyRepository.findVacancyIdByCompanyId(companyId).let { vacancyId ->
            vacancyResponseRepository.findAllUsernameByVacancyId(vacancyId, PageRequest.of(pageNumber, pageSize!!))
        }
    }

}