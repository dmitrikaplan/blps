package ru.kaplaan.consumer.domain.properties.quartz

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "quartz.send-payment-order")
data class SendPaymentOrderProperties
    @ConstructorBinding constructor(
        val jobName: String,
        val triggerName: String
    )