package ru.kaplaan.authserver.domain.config

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessagePostProcessor
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
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

    @Bean
    fun connectionFactory(): ConnectionFactory =
        CachingConnectionFactory(hostname, port!!)


    @Bean
    fun rabbitMqTemplate(): RabbitTemplate =
        RabbitTemplate(connectionFactory()).apply{
            setBeforePublishPostProcessors(object:MessagePostProcessor{
                override fun postProcessMessage(message: Message): Message {

                    return message.apply {
                        messageProperties.setHeader("trustedPackages", "java.util")
                    }
                }

            })
        }
}