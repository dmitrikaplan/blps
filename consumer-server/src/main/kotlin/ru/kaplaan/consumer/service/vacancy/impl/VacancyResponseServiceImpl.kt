package ru.kaplaan.consumer.service.vacancy.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import ru.kaplaan.consumer.domain.exception.PermissionDeniedException
import ru.kaplaan.consumer.domain.exception.alreadyExists.VacancyResponseAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.VacancyResponseNotFoundException
import ru.kaplaan.consumer.repository.vacancy.VacancyResponseRepository
import ru.kaplaan.consumer.service.data.CompanyDataService
import ru.kaplaan.consumer.service.data.UserDataService
import ru.kaplaan.consumer.service.email.EmailService
import ru.kaplaan.consumer.service.payment.PaymentOrderService
import ru.kaplaan.consumer.service.vacancy.VacancyResponseService
import ru.kaplaan.consumer.service.vacancy.VacancyService
import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseStatus

@Service
class VacancyResponseServiceImpl(
    private val vacancyResponseRepository: VacancyResponseRepository,
    private val vacancyService: VacancyService,
    private val emailService: EmailService,
    private val userDataService: UserDataService,
    private val companyDataService: CompanyDataService,
    private val paymentOrderService: PaymentOrderService,
) : VacancyResponseService {

    @Value("\${page-size.vacancy-response}")
    var pageSize: Int? = null

    @Transactional
    override fun save(vacancyResponse: VacancyResponse): VacancyResponse {
        vacancyResponseRepository.findVacancyResponseById(vacancyResponse.pk)?.let {
            throw VacancyResponseAlreadyExistsException()
        }

        val vacancy = vacancyService.getVacancyById(vacancyResponse.pk.vacancyId)
        val userData = userDataService.getUserDataByUserId(vacancyResponse.pk.userId)

        vacancyResponseRepository.saveVacancyResponse(vacancyResponse)
        emailService.sendVacancyResponseMail(vacancyResponse, vacancy, userData)

        return vacancyResponse

    }

    @Transactional
    override fun update(vacancyResponse: VacancyResponse, companyId: Long): VacancyResponse {

        if (!vacancyService.existsVacancyByVacancyIdAndCompanyId(vacancyResponse.pk.vacancyId, companyId))
            throw PermissionDeniedException()

        if (!vacancyResponseRepository.existsById(vacancyResponse.pk))
            throw VacancyResponseNotFoundException()

        val previousVacancyResponseStatus = getVacancyResponseById(vacancyResponse.pk).status

        vacancyResponseRepository.updateVacancyResponse(vacancyResponse)

        val vacancy = vacancyService.getVacancyById(vacancyResponse.pk.vacancyId)
        val userData = userDataService.getUserDataByUserId(vacancyResponse.pk.userId)

        emailService.sendVacancyResponseMail(vacancyResponse, vacancy, userData)

        if (
            vacancyResponse.status == VacancyResponseStatus.ACCEPTED &&
            previousVacancyResponseStatus != VacancyResponseStatus.ACCEPTED
        ) {
            val email: String = companyDataService.getCompanyDataByCompanyId(vacancy.companyId!!).contactPerson.email
            paymentOrderService.generatePaymentOrder(vacancy.companyId!!).also {
                emailService.sendPaymentOrder(email, it)
            }
        }
        return vacancyResponse
    }

    @Transactional
    override fun delete(vacancyId: Long, userId: Long) =
        vacancyResponseRepository.deleteById(VacancyResponse.PK(vacancyId, userId))

    @Transactional
    override fun getVacancyResponseById(pk: VacancyResponse.PK): VacancyResponse {
        return vacancyResponseRepository.findVacancyResponseById(pk)
            ?: throw VacancyResponseNotFoundException()
    }

    @Transactional
    override fun getAllVacancyResponsesByUserId(userId: Long): List<VacancyResponse> {
        return vacancyResponseRepository.findAllVacancyResponsesByUserId(userId)
    }

    @Transactional
    override fun getVacancyResponseByIdAndCompanyId(companyId: Long, pk: VacancyResponse.PK): VacancyResponse {
        checkVacancyOwner(pk.vacancyId, companyId)

        return vacancyResponseRepository.findVacancyResponseById(pk)
            ?: throw VacancyResponseNotFoundException()
    }


    @Transactional
    override fun getAllUserIdByVacancyIdAndCompanyId(
        vacancyId: Long,
        companyId: Long,
        pageNumber: Int,
    ): List<Long> {
        checkVacancyOwner(vacancyId, companyId)
        return vacancyResponseRepository.findAllUserIdByVacancyId(vacancyId, PageRequest.of(pageNumber, pageSize!!))
    }


    private fun checkVacancyOwner(vacancyId: Long, companyId: Long) {
        if (!vacancyService.existsVacancyByVacancyIdAndCompanyId(vacancyId, companyId))
            throw PermissionDeniedException()
    }

}