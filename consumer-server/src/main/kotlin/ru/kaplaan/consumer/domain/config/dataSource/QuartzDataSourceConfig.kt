package ru.kaplaan.consumer.domain.config.dataSource

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import ru.kaplaan.consumer.domain.properties.QuartzDataSourceProperties
import javax.sql.DataSource

@Configuration
class QuartzDataSourceConfig(
    private val quartzDataSourceProperties: QuartzDataSourceProperties
) {
    @Bean
    fun quartzDataSource(): DataSource =
        DataSourceBuilder
            .create()
            .url(quartzDataSourceProperties.url)
            .username(quartzDataSourceProperties.username)
            .password(quartzDataSourceProperties.password)
            .build()

    @Bean
    fun quartzDataSourceInitializer(): DataSourceInitializer{
        val dataBasePopulator = ResourceDatabasePopulator().apply {
            addScripts(ClassPathResource("sql/quartz/schema.sql"))
        }
        return DataSourceInitializer().apply {
            this.setDataSource(quartzDataSource())
            this.setDatabasePopulator(dataBasePopulator)
        }
    }


}