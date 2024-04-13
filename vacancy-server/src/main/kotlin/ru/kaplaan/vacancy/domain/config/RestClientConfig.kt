package ru.kaplaan.vacancy.domain.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatusCode
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClient.ResponseSpec.ErrorHandler

@Configuration
class RestClientConfig {

    @Bean
    fun restClient(): RestClient =
        RestClient.builder()
            .defaultStatusHandler(DefaultResponseErrorHandler())
            .build()
}