package ru.kaplaan.authserver.domain.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "rabbit.mail-server.activate-account")
data class ActivateAccountProperties
    @ConstructorBinding constructor(
        val queueName: String,
        val routingKey: String
    )