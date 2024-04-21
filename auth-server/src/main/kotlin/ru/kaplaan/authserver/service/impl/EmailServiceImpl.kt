package ru.kaplaan.authserver.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.kaplaan.authserver.service.EmailService
import ru.kaplaan.authserver.web.dto.email.ActivateAccountByEmailDto

@Service
class EmailServiceImpl(
    private val amqpTemplate: AmqpTemplate
) : EmailService {


    @Value("\${rabbit.mail-server.exchange-name}")
    private lateinit var activateAccountByEmailExchangeName: String

    @Value("\${rabbit.mail-server.activate-account.routing-key}")
    private lateinit var activateAccountByEmailRoutingKey: String

    override fun activateUserByEmail(emailTo: String, login: String, activationCode: String) {
        ObjectMapper().writeValueAsString(
            ActivateAccountByEmailDto(emailTo, login, activationCode)
        ).also { json ->
            amqpTemplate.convertAndSend(activateAccountByEmailExchangeName, activateAccountByEmailRoutingKey, json)
        }
    }

    override fun recoveryPasswordByEmail(emailTo: String, login: String, activationCode: String) {
       TODO()
    }

}
