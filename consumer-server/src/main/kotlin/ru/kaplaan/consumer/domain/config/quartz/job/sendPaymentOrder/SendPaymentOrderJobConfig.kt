package ru.kaplaan.consumer.domain.config.quartz.job.sendPaymentOrder

import org.quartz.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kaplaan.consumer.domain.properties.quartz.SchedulerProperties
import ru.kaplaan.consumer.domain.properties.quartz.SendPaymentOrderProperties

@Configuration
class SendPaymentOrderJobConfig(
    private val schedulerProperties: SchedulerProperties,
    private val sendPaymentOrderProperties: SendPaymentOrderProperties
) {

    @Bean
    fun sendPaymentOrderJob(): JobDetail =
        JobBuilder
            .newJob(SendPaymentOrderJob::class.java)
            .withIdentity(sendPaymentOrderProperties.jobName, schedulerProperties.permanentJobsGroupName)
            .storeDurably()
            .requestRecovery(true)
            .build()

    @Bean
    fun sendPaymentOrderTrigger(): Trigger =
        TriggerBuilder
            .newTrigger()
            .forJob(sendPaymentOrderJob())
            .withIdentity(sendPaymentOrderProperties.triggerName, schedulerProperties.permanentJobsGroupName)
            .withSchedule(CronScheduleBuilder.cronSchedule(schedulerProperties.sendPaymentOrderJobCron))
            .build()
}