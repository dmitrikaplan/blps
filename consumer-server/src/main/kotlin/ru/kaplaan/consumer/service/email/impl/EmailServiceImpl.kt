package ru.kaplaan.consumer.service.email.impl

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import ru.kaplaan.consumer.service.email.EmailService
import ru.kaplaan.consumer.web.dto.email.VacancyResponseEmailDto

@Service
class EmailServiceImpl(
    private val amqpTemplate: AmqpTemplate,
) : EmailService {

    @Value("\${rabbit.mail-server.exchange-name}")
    private lateinit var exchangeName: String

    @Value("\${rabbit.mail-server.send-vacancy-response-mail.routing-key}")
    private lateinit var routingKey: String

    override fun sendVacancyResponseMail(vacancyResponse: VacancyResponse, vacancy: Vacancy, userData: UserData) {

        VacancyResponseEmailDto(
            email = userData.email,
            firstname = userData.firstname,
            surname = userData.surname,
            vacancyTitle = vacancy.title,
            comment = vacancyResponse.comment,
            status = vacancyResponse.status.name
        ).also { vacancyResponseEmailDto ->
            ObjectMapper().writeValueAsString(vacancyResponseEmailDto).let { json ->
                amqpTemplate.convertAndSend(exchangeName, routingKey, json)
            }
        }
    }
}