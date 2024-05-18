package ru.kaplaan.consumer.domain.config.quartz

import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.quartz.QuartzProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import ru.kaplaan.consumer.domain.properties.SchedulerProperties
import java.util.*
import javax.sql.DataSource


@Configuration
class SchedulerConfig(
    private val schedulerProperties: SchedulerProperties,
    @Qualifier("quartzDataSource")
    private val dataSource: DataSource,
    private val quartzProperties: QuartzProperties
) {
    @Bean
    fun schedulerFactoryBean(): SchedulerFactoryBean {
        val properties = Properties()
        properties.putAll(quartzProperties.properties)

        val factory = SchedulerFactoryBean()
        factory.setOverwriteExistingJobs(true)
        factory.setDataSource(dataSource)
        factory.setQuartzProperties(properties)
        return factory
    }

    @Bean
    fun scheduler(triggers: List<Trigger>, jobDetails: List<JobDetail>, factory: SchedulerFactoryBean): Scheduler {
        factory.setWaitForJobsToCompleteOnShutdown(true)
        val scheduler = factory.scheduler
        revalidateJobs(jobDetails, scheduler)
        rescheduleTriggers(triggers, scheduler)
        scheduler.start()
        return scheduler
    }

    fun rescheduleTriggers(triggers: List<Trigger>, scheduler: Scheduler) {
        triggers.forEach {
            if (!scheduler.checkExists(it.key)) {
                scheduler.scheduleJob(it)
            } else {
                scheduler.rescheduleJob(it.key, it)
            }
        }
    }

    fun revalidateJobs(jobDetails: List<JobDetail>, scheduler: Scheduler) {
        val jobKeys = jobDetails.map { it.key }
        scheduler.getJobKeys(GroupMatcher.jobGroupEquals(schedulerProperties.permanentJobsGroupName)).forEach {
            if (it !in jobKeys) {
                scheduler.deleteJob(it)
            }
        }
    }
}