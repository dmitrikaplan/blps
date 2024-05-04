package ru.kaplaan.consumer.service.email.impl

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import ru.kaplaan.consumer.service.email.EmailService
import ru.kaplaan.consumer.web.dto.email.SuccessPaymentEmailDto
import ru.kaplaan.consumer.web.dto.email.VacancyResponseEmailDto
import ru.kaplaan.consumer.web.mapper.payment.toEmailDto

@Service
class EmailServiceImpl(
    private val amqpTemplate: AmqpTemplate,
) : EmailService {

    @Value("\${rabbit.mail-server.exchange-name}")
    private lateinit var emailServerExchangeName: String

    @Value("\${rabbit.mail-server.send-vacancy-response.routing-key}")
    private lateinit var sendVacancyResponseEmailRoutingKey: String

    @Value("\${rabbit.mail-server.send-payment-order.routing-key}")
    private lateinit var sendPaymentOrderRoutingKey: String

    @Value("\${rabbit.mail-server.send-info-about-success-payment.routing-key}")
    private lateinit var sendInfoAboutSuccessPaymentRoutingKey: String

    override fun sendVacancyResponseMail(vacancyResponse: VacancyResponse, vacancy: Vacancy, userData: UserData) {

        VacancyResponseEmailDto(
            email = userData.email,
            firstname = userData.firstname,
            surname = userData.surname,
            vacancyTitle = vacancy.title,
            comment = vacancyResponse.comment,
            status = vacancyResponse.status.toString()
        ).also { vacancyResponseEmailDto ->
            ObjectMapper().writeValueAsString(vacancyResponseEmailDto).let { json ->
                amqpTemplate.convertAndSend(emailServerExchangeName, sendVacancyResponseEmailRoutingKey, json)
            }
        }
    }

    override fun sendPaymentOrder(email: String, paymentOrder: PaymentOrder) {
        paymentOrder.toEmailDto(email).also {  paymentOrderEmailDto ->
            ObjectMapper().writeValueAsString(paymentOrderEmailDto).let { json ->
                amqpTemplate.convertAndSend(emailServerExchangeName, sendPaymentOrderRoutingKey, json)
            }
        }
    }

    override fun sendInfoAboutSuccessPayment(email: String, companyName: String, paymentOrderId: Long) {
        SuccessPaymentEmailDto(
            email = email,
            companyName = companyName,
            paymentOrderId = paymentOrderId
        ).also { successPaymentEmailDto ->
            ObjectMapper().writeValueAsString(successPaymentEmailDto).let { json ->
                amqpTemplate.convertAndSend(emailServerExchangeName, sendInfoAboutSuccessPaymentRoutingKey, json)
            }
        }
    }
}