package ru.kaplaan.consumer.domain.properties.quartz

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "scheduler")
data class SchedulerProperties @ConstructorBinding constructor(
    val permanentJobsGroupName: String,
    val sendPaymentOrderJobCron: String,
)