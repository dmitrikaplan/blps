package ru.kaplaan.authserver.domain.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfiguration {

    @Value("\${rabbit.mail-server.exchange-name}")
    private lateinit var activateAccountByEmailExchangeName: String

    @Value("\${rabbit.mail-server.activate-account.queue-name}")
    private lateinit var activateAccountByEmailQueueName: String

    @Value("\${rabbit.mail-server.activate-account.routing-key}")
    private lateinit var activateAccountByEmailRoutingKey: String


    @Bean
    fun directExchange(): DirectExchange =
        DirectExchange(activateAccountByEmailExchangeName)

    @Bean
    fun activateAccountQueue(): Queue =
        Queue(activateAccountByEmailQueueName)


    @Bean
    fun activateAccountByEmailBinding(queue: Queue, exchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(exchange)
            .with(activateAccountByEmailRoutingKey)

}