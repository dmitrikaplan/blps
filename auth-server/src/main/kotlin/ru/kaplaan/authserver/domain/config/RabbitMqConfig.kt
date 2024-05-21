package ru.kaplaan.authserver.domain.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kaplaan.authserver.domain.properties.ActivateAccountProperties
import kotlin.math.truncate

@Configuration
class RabbitMqConfig(
    private val activateAccountProperties: ActivateAccountProperties
) {

    @Value("\${rabbit.mail-server.exchange-name}")
    private lateinit var activateAccountByEmailExchangeName: String


    @Bean
    fun directExchange(): DirectExchange =
        ExchangeBuilder
            .directExchange(activateAccountByEmailExchangeName)
            .build()

    @Bean
    fun activateAccountQueue(): Queue =
        QueueBuilder
            .durable(activateAccountProperties.queueName)
            .build()


    @Bean
    fun activateAccountByEmailBinding(queue: Queue, exchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(exchange)
            .with(activateAccountProperties.queueName)

}