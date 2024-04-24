package ru.kaplaan.consumer.domain.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
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

    @Value("\${rabbit.mail-server.send-vacancy-response-mail.queue-name}")
    private lateinit var sendVacancyResponseMailQueueName: String

    @Value("\${rabbit.mail-server.send-vacancy-response-mail.routing-key}")
    private lateinit var sendVacancyResponseMailRoutingKey: String

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
        Queue(sendVacancyResponseMailQueueName)


    @Bean
    fun sendVacancyResponseMailBinding(queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(sendVacancyResponseMailRoutingKey)

}