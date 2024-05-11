package ru.kaplaan.consumer.domain.config.datasource

import com.atomikos.jdbc.AtomikosDataSourceBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.core.io.ClassPathResource
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator

@Configuration
@EnableJdbcRepositories(basePackages = ["ru.kaplaan.consumer.repository.consumer"])
class ConsumerDatasourceConfig {

    @Value("\${consumer-datasource.username}")
    private lateinit var username: String

    @Value("\${consumer-datasource.password}")
    private lateinit var password: String

    @Value("\${consumer-datasource.driver-class-name}")
    private lateinit var driverClassName: String

    @Value("\${consumer-datasource.url}")
    private lateinit var url: String

    @Value("\${consumer-datasource.unique-resource-name}")
    private lateinit var uniqueResourceName: String

    @Value("\${consumer-datasource.xa-datasource-class-name}")
    private lateinit var xaDataSourceClassName: String

    @Bean
    @Primary
    fun consumerDataSource(): AtomikosDataSourceBean =
        AtomikosDataSourceBean().apply {
            uniqueResourceName = this@ConsumerDatasourceConfig.uniqueResourceName
            xaDataSourceClassName = this@ConsumerDatasourceConfig.xaDataSourceClassName
            xaProperties.apply {
                this["URL"] = url
                this["user"] = username
                this["password"] = password
            }
        }

    @Bean
    @Primary
    fun consumerDataSourceInitializer(): DataSourceInitializer {
        val resourceDatabasePopulator = ResourceDatabasePopulator().apply {
            this.addScript(ClassPathResource("sql/consumer/schema.sql"))
        }
        return DataSourceInitializer().apply {
            setDataSource(consumerDataSource())
            setDatabasePopulator(resourceDatabasePopulator)
        }
    }

}