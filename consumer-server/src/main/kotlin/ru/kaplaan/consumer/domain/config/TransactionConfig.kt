package ru.kaplaan.consumer.domain.config

import com.atomikos.icatch.jta.UserTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.jta.JtaTransactionManager


@Configuration
@EnableTransactionManagement
class TransactionConfig {

    @Bean
    fun userTransactionManager(): UserTransactionManager {
        return UserTransactionManager().apply {
            setTransactionTimeout(300)
            forceShutdown = true
        }
    }

    @Bean
    fun transactionManager(): JtaTransactionManager {
        return JtaTransactionManager().apply {
            this.transactionManager = userTransactionManager()
            this.userTransaction = userTransactionManager()
        }
    }
}