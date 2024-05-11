package ru.kaplaan.consumer.domain.config.datasource

import com.atomikos.jdbc.AtomikosDataSourceBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator

@Configuration
@EnableJdbcRepositories(basePackages = ["ru.kaplaan.consumer.repository.billing"])
class BillingDatasourceConfig {

    @Value("\${billing-datasource.url}")
    private lateinit var url: String

    @Value("\${billing-datasource.username}")
    private lateinit var username: String

    @Value("\${billing-datasource.password}")
    private lateinit var password: String

    @Value("\${billing-datasource.driver-class-name}")
    private lateinit var driverClassName: String

    @Value("\${billing-datasource.xa-datasource-class-name}")
    private lateinit var xaDataSourceClassName: String

    @Value("\${billing-datasource.unique-resource-name}")
    private lateinit var uniqueResourceName: String


    @Bean
    fun billingDataSource(): AtomikosDataSourceBean =
        AtomikosDataSourceBean().apply {
            uniqueResourceName = this@BillingDatasourceConfig.uniqueResourceName
            xaDataSourceClassName = this@BillingDatasourceConfig.xaDataSourceClassName
            xaProperties.apply {
                this["URL"] = url
                this["user"] = username
                this["password"] = password
            }
        }

    @Bean
    fun billingDataSourceInitializer(): DataSourceInitializer{
        val resourceDatabasePopulator = ResourceDatabasePopulator().apply {
            addScripts(ClassPathResource("sql/billing/schema.sql"))
        }

        return DataSourceInitializer().apply {
            setDataSource(billingDataSource())
            setDatabasePopulator(resourceDatabasePopulator)
        }
    }
}