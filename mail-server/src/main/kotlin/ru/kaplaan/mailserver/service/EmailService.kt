package ru.kaplaan.mailserver.service


import org.springframework.stereotype.Service
import ru.kaplaan.mailserver.web.dto.auth.ActivateAccountByEmailDto
import ru.kaplaan.mailserver.web.dto.paymentOrder.PaymentOrderEmailDto
import ru.kaplaan.mailserver.web.dto.vacancyResponse.VacancyResponseEmailDto

@Service
interface EmailService {
    fun activateUserByEmail(activateAccountByEmailDto: ActivateAccountByEmailDto)

    fun notifyAboutUpdateVacancyResponseStatus(vacancyResponseEmailDto: VacancyResponseEmailDto)
    fun sendPaymentOrder(paymentOrderEmailDto: PaymentOrderEmailDto)
}
