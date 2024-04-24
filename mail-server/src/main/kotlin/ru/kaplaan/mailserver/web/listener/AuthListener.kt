package ru.kaplaan.mailserver.web.listener

import kotlinx.serialization.json.Json
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.kaplaan.mailserver.service.EmailService
import ru.kaplaan.mailserver.web.dto.auth.ActivateAccountByEmailDto

@Component
@EnableRabbit
class AuthListener(
    private val emailService: EmailService
) {

    @RabbitListener(queues = ["activate-account-queue"])
    fun activateUserAccount(json: String){
        Json.decodeFromString<ActivateAccountByEmailDto>(json).also {
            emailService.activateUserByEmail(it.emailTo, it.login, it.activationCode)
        }
    }
}