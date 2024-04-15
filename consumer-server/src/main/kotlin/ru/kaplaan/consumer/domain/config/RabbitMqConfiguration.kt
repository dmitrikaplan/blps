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

    @Value("\${rabbit.user-info.get-user-id-by-username.queue-name}")
    private lateinit var getUserIdByUsernameQueueName: String

    @Value("\${rabbit.user-info.get-user-id-by-username.routing-key}")
    private lateinit var getUserIdByUsernameRoutingKey: String

    @Value("\${rabbit.user-info.get-username-by-user-id.queue-name}")
    private lateinit var getUsernameByUserIdQueueName: String

    @Value("\${rabbit.user-info.get-username-by-user-id.routing-key}")
    private lateinit var getUsernameByUserIdRoutingKey: String

    @Value("\${rabbit.user-info.get-all-usernames-by-users-id.queue-name}")
    private lateinit var getAllUsernamesByUsersIdQueueName: String

    @Value("\${rabbit.user-info.get-all-usernames-by-users-id.routing-key}")
    private lateinit var getAllUsernamesByUsersIdRoutingKey: String

    @Value("\${rabbit.user-info.exchange-name}")
    private lateinit var userDataExchangeName: String

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
    fun userDataExchange(): DirectExchange =
        DirectExchange(userDataExchangeName)

    @Bean
    fun getUserIdByUsernameQueue(): Queue =
        Queue(getUserIdByUsernameQueueName)

    @Bean
    fun getUsernameByUserIdQueue(): Queue =
        Queue(getUsernameByUserIdQueueName)

    @Bean
    fun getAllUsernamesByUsersIdQueue(): Queue =
        Queue(getAllUsernamesByUsersIdQueueName)


    @Bean
    fun getUserIdByUsernameBinding(@Qualifier("getUserIdByUsernameQueue") queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(getUserIdByUsernameRoutingKey)


    @Bean
    fun getUsernameByUserIdBinding(@Qualifier("getUsernameByUserIdQueue") queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(getUsernameByUserIdRoutingKey)


    @Bean
    fun getAllUsernamesByUsersIdBinding(@Qualifier("getAllUsernamesByUsersIdQueue") queue: Queue, directExchange: DirectExchange): Binding =
        BindingBuilder
            .bind(queue)
            .to(directExchange)
            .with(getAllUsernamesByUsersIdRoutingKey)
}