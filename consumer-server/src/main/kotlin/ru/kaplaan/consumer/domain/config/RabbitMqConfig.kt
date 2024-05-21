package ru.kaplaan.consumer.domain.config

import org.springframework.amqp.core.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kaplaan.consumer.domain.properties.email.SendInfoAboutSuccessPaymentProperties
import ru.kaplaan.consumer.domain.properties.email.SendPaymentOrderProperties
import ru.kaplaan.consumer.domain.properties.email.SendVacancyResponseProperties

@Configuration
class RabbitMqConfig(
    private val sendInfoAboutSuccessPaymentProperties: SendInfoAboutSuccessPaymentProperties,
    private val sendPaymentOrderProperties: SendPaymentOrderProperties,
    private val sendVacancyResponseProperties: SendVacancyResponseProperties
) {

    @Value("\${rabbit.mail-server.exchange-name}")
    private lateinit var mailServerExchangeName: String

    @Bean
    fun mailServerExchange(): DirectExchange =
        ExchangeBuilder
            .directExchange(mailServerExchangeName)
            .build()

    @Bean
    fun sendVacancyResponseMailQueue(): Queue =
        QueueBuilder
            .durable(sendVacancyResponseProperties.queueName)
            .build()

    @Bean
    fun sendPaymentOrderQueue(): Queue =
        QueueBuilder
            .durable(sendPaymentOrderProperties.queueName)
            .build()

    @Bean
    fun sendInfoAboutSuccessPaymentQueue(): Queue =
        QueueBuilder
            .durable(sendInfoAboutSuccessPaymentProperties.queueName)
            .build()


    @Bean
    fun sendVacancyResponseMailBinding(@Qualifier("sendVacancyResponseMailQueue") queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(sendVacancyResponseProperties.routingKey)


    @Bean
    fun sendPaymentOrderBinding(@Qualifier("sendPaymentOrderQueue") queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(sendPaymentOrderProperties.routingKey)


    @Bean
    fun sendInfoAboutSuccessPaymentBinding(@Qualifier("sendInfoAboutSuccessPaymentQueue") queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(sendInfoAboutSuccessPaymentProperties.routingKey)

}
