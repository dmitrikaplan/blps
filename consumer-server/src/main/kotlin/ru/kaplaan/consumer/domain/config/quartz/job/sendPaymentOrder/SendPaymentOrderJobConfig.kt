package ru.kaplaan.consumer.domain.config.quartz.job.sendPaymentOrder

import org.quartz.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kaplaan.consumer.domain.properties.SchedulerProperties

@Configuration
class SendPaymentOrderJobConfig(
    private val schedulerProperties: SchedulerProperties
) {


    @Bean
    fun sendPaymentOrderJob(): JobDetail =
        JobBuilder
            .newJob(SendPaymentOrderJob::class.java)
            .withIdentity("sendPaymentOrder", schedulerProperties.permanentJobsGroupName)
            .storeDurably()
            .requestRecovery(true)
            .build()

    @Bean
    fun sendPaymentOrderTrigger(): Trigger =
        TriggerBuilder
            .newTrigger()
            .forJob(sendPaymentOrderJob())
            .withIdentity("sendPaymentOrderTrigger", schedulerProperties.permanentJobsGroupName)
            .withSchedule(CronScheduleBuilder.cronSchedule(schedulerProperties.sendPaymentOrderJobCron))
            .build()
}