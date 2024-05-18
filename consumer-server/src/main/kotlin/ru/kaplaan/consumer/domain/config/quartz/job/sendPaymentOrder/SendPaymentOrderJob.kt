package ru.kaplaan.consumer.domain.config.quartz.job.sendPaymentOrder

import org.quartz.DisallowConcurrentExecution
import org.quartz.JobExecutionContext
import org.quartz.PersistJobDataAfterExecution
import org.springframework.scheduling.quartz.QuartzJobBean
import ru.kaplaan.consumer.service.data.CompanyDataService
import ru.kaplaan.consumer.service.email.EmailService
import ru.kaplaan.consumer.service.payment.PaymentOrderService
import ru.kaplaan.consumer.service.vacancy.VacancyResponseService
import ru.kaplaan.consumer.service.vacancy.VacancyService
import java.time.LocalDate

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
class SendPaymentOrderJob(
    private val paymentOrderService: PaymentOrderService,
    private val vacancyResponseService: VacancyResponseService,
    private val vacancyService: VacancyService,
    private val companyDataService: CompanyDataService,
    private val emailService: EmailService
): QuartzJobBean() {
    override fun executeInternal(context: JobExecutionContext) {
        val date = LocalDate.now()
        vacancyResponseService.getAllVacancyIdByDateLastStatusUpdateBetweenAndAccepted(date.minusMonths(1), date)
        .forEach { vacancyId ->
            val count = vacancyResponseService.countVacancyResponsesByDateBetweenAndVacancyIdAndAccepted(date.minusMonths(1), date, vacancyId)
            val companyId = vacancyService.getVacancyById(vacancyId).companyId!!
            val email: String = companyDataService.getCompanyDataByCompanyId(companyId).contactPerson.email
            paymentOrderService.generatePaymentOrder(companyId, count).also {
                emailService.sendPaymentOrder(email, it)
            }
        }

    }

}