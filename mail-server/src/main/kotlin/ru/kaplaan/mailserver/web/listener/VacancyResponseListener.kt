package ru.kaplaan.mailserver.web.listener

import kotlinx.serialization.json.Json
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.kaplaan.mailserver.service.EmailService
import ru.kaplaan.mailserver.web.dto.vacancyResponse.VacancyResponseEmailDto

@Component
@EnableRabbit
class VacancyResponseListener(
    private val emailService: EmailService
) {

    @RabbitListener(queues = ["send-vacancy-response-mail-queue"])
    fun sendVacancyResponseMail(json: String){
        Json.decodeFromString<VacancyResponseEmailDto>(json).let { vacancyResponseEmailDto ->
            emailService.notifyAboutUpdateVacancyResponseStatus(vacancyResponseEmailDto)
        }
    }
}