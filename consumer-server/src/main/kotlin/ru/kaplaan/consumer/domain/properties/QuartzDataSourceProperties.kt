package ru.kaplaan.consumer.domain.properties

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties(prefix = "quartz.datasource")
data class QuartzDataSourceProperties(
    val username: String,
    val password: String,
    val url: String,
    val driverClassName: String
)