package ru.kaplaan.consumer.service.vacancy.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import ru.kaplaan.consumer.domain.exception.PermissionDeniedException
import ru.kaplaan.consumer.domain.exception.alreadyExists.VacancyResponseAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.VacancyResponseNotFoundException
import ru.kaplaan.consumer.repository.vacancy.VacancyResponseRepository
import ru.kaplaan.consumer.service.accountant.AccountantService
import ru.kaplaan.consumer.service.data.UserDataService
import ru.kaplaan.consumer.service.email.EmailService
import ru.kaplaan.consumer.service.vacancy.VacancyResponseService
import ru.kaplaan.consumer.service.vacancy.VacancyService
import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseStatus

@Service
class VacancyResponseServiceImpl(
    private val vacancyResponseRepository: VacancyResponseRepository,
    private val vacancyService: VacancyService,
    private val emailService: EmailService,
    private val userDataService: UserDataService,
    private val accountantService: AccountantService
) : VacancyResponseService {

    @Value("\${vacancy-response.page-size}")
    var pageSize: Int? = null

    override fun save(vacancyResponse: VacancyResponse): VacancyResponse {

        val vacancy = vacancyService.getVacancyById(vacancyResponse.pk.vacancyId)
        val userData = userDataService.getUserDataByUserId(vacancyResponse.pk.userId)

        vacancyResponseRepository.findVacancyResponseById(vacancyResponse.pk)?.let {
            throw VacancyResponseAlreadyExistsException()
        }

        vacancyResponseRepository.saveVacancyResponse(vacancyResponse)
        emailService.sendVacancyResponseMail(vacancyResponse, vacancy, userData)

        return vacancyResponse

    }

    override fun update(vacancyResponse: VacancyResponse): VacancyResponse {

        if(!vacancyResponseRepository.existsById(vacancyResponse.pk))
            throw VacancyResponseNotFoundException()

        val vacancy = vacancyService.getVacancyById(vacancyResponse.pk.vacancyId)
        val userData = userDataService.getUserDataByUserId(vacancyResponse.pk.userId)

        vacancyResponseRepository.updateVacancyResponse(vacancyResponse)
        emailService.sendVacancyResponseMail(vacancyResponse, vacancy, userData)

        if(vacancyResponse.status == VacancyResponseStatus.ACCEPTED){
            accountantService.sendPaymentOrder(vacancy.companyId!!)
        }
        return vacancyResponse
    }

    override fun delete(vacancyId: Long, userId: Long) =
        vacancyResponseRepository.deleteById(VacancyResponse.PK(vacancyId, userId))

    override fun getVacancyResponseById(pk: VacancyResponse.PK): VacancyResponse {
        return vacancyResponseRepository.findVacancyResponseById(pk)
            ?: throw VacancyResponseNotFoundException()
    }

    override fun getAllVacancyResponsesByUserId(userId: Long): List<VacancyResponse> {
        return vacancyResponseRepository.findAllVacancyResponsesByUserId(userId)
    }

    override fun getVacancyResponseByIdAndCompanyId(companyId: Long, pk: VacancyResponse.PK): VacancyResponse {
        checkVacancyOwner(pk.vacancyId, companyId)

        return vacancyResponseRepository.findVacancyResponseById(pk)
            ?: throw VacancyResponseNotFoundException()
    }


    override fun getAllUserIdByVacancyIdAndCompanyId(
        vacancyId: Long,
        companyId: Long,
        pageNumber: Int,
    ): List<Long> {
        checkVacancyOwner(vacancyId, companyId)

        return vacancyResponseRepository.findAllUserIdByVacancyId(vacancyId, PageRequest.of(pageNumber, pageSize!!))
    }


    private fun checkVacancyOwner(vacancyId: Long, companyId: Long){
        if(!vacancyService.existsVacancyByVacancyIdAndCompanyId(vacancyId, companyId))
            throw PermissionDeniedException()
    }

}