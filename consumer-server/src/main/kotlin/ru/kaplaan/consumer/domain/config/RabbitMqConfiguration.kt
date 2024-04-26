package ru.kaplaan.consumer.domain.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfiguration {

    @Value("\${rabbit.hostname}")
    private lateinit var hostname: String

    @Value("\${rabbit.port}")
    private var port: Int? = null

    @Value("\${rabbit.mail-server.exchange-name}")
    private lateinit var mailServerExchangeName: String

    @Value("\${rabbit.mail-server.send-vacancy-response.queue-name}")
    private lateinit var sendVacancyResponseQueueName: String

    @Value("\${rabbit.mail-server.send-vacancy-response.routing-key}")
    private lateinit var sendVacancyResponseRoutingKey: String

    @Value("\${rabbit.mail-server.send-payment-order.queue-name}")
    private lateinit var sendPaymentOrderQueueName: String

    @Value("\${rabbit.mail-server.send-payment-order.routing-key}")
    private lateinit var sendPaymentOrderRoutingKey: String

    @Bean
    fun connectionFactory(): ConnectionFactory =
        CachingConnectionFactory(hostname, port!!)

    @Bean
    fun amqpAdmin(): AmqpAdmin =
        RabbitAdmin(connectionFactory())

    @Bean
    fun rabbitTemplate(): RabbitTemplate =
        RabbitTemplate(connectionFactory())

    @Bean
    fun mailServerExchange(): DirectExchange =
        DirectExchange(mailServerExchangeName)

    @Bean
    fun sendVacancyResponseMailQueue(): Queue =
        Queue(sendVacancyResponseQueueName)

    @Bean
    fun sendPaymentOrderQueue(): Queue =
        Queue(sendPaymentOrderQueueName)


    @Bean
    fun sendVacancyResponseMailBinding(@Qualifier("sendVacancyResponseMailQueue") queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(sendVacancyResponseRoutingKey)


    @Bean
    fun sendPaymentOrderBinding(@Qualifier("sendPaymentOrderQueue") queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(sendPaymentOrderRoutingKey)

}