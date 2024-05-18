package ru.kaplaan.consumer.domain.config.quartz.job.sendPaymentOrder

import org.quartz.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kaplaan.consumer.domain.properties.SchedulerProperties

@Configuration
class   SendPaymentOrderJobConfig(
    private val schedulerProperties: SchedulerProperties
) {

    @Value("quartz.send-payment-order.job-name")
    private lateinit var jobName: String


    @Value("quartz.send-payment-order.trigger-name")
    private lateinit var triggerName: String


    @Bean
    fun sendPaymentOrderJob(): JobDetail =
        JobBuilder
            .newJob(SendPaymentOrderJob::class.java)
            .withIdentity(jobName, schedulerProperties.permanentJobsGroupName)
            .storeDurably()
            .requestRecovery(true)
            .build()

    @Bean
    fun sendPaymentOrderTrigger(): Trigger =
        TriggerBuilder
            .newTrigger()
            .forJob(sendPaymentOrderJob())
            .withIdentity(triggerName, schedulerProperties.permanentJobsGroupName)
            .withSchedule(CronScheduleBuilder.cronSchedule(schedulerProperties.sendPaymentOrderJobCron))
            .build()
}