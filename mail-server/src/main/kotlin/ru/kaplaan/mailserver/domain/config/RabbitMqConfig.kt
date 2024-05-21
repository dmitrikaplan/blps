package ru.kaplaan.mailserver.domain.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig{


    @Value("rabbit.activate-account.queue-name")
    lateinit var activateAccountQueueName: String

    @Value("rabbit.send-info-about-success-payment.queue-name")
    lateinit var sendInfoAboutSuccessPaymentQueueName: String

    @Value("rabbit.send-payment-order-queue-name")
    lateinit var sendPaymentOrderQueueName: String

    @Value("rabbit.send-vacancy-response.queue-name")
    lateinit var sendVacancyResponseQueueName: String

    @Bean
    fun activateAccountQueue(): Queue =
        QueueBuilder
            .durable(activateAccountQueueName)
            .build()

    @Bean
    fun sendInfoAboutSuccessPaymentQueue(): Queue =
        QueueBuilder
            .durable(sendInfoAboutSuccessPaymentQueueName)
            .build()

    @Bean
    fun sendPaymentOrderQueue(): Queue =
        QueueBuilder
            .durable(sendPaymentOrderQueueName)
            .build()


    @Bean
    fun sendVacancyResponseQueue(): Queue =
        QueueBuilder
            .durable(sendVacancyResponseQueueName)
            .build()

}