package ru.kaplaan.consumer

import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import ru.kaplaan.consumer.domain.entity.payment.CompanyPaymentInfo
import ru.kaplaan.consumer.domain.properties.paymentInfo.CompanyPaymentInfoProperties
import ru.kaplaan.consumer.service.payment.CompanyPaymentInfoService

@SpringBootApplication
@EnableTransactionManagement
@ConfigurationPropertiesScan(basePackages = ["ru.kaplaan.consumer.domain.properties"])
class ConsumerServerApplication(
    private val companyPaymentInfoService: CompanyPaymentInfoService,
    private val companyPaymentInfoProperties: CompanyPaymentInfoProperties
){

    private val log = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    fun initCompanyPaymentInfo(){
        CompanyPaymentInfo().apply {
            this.inn = companyPaymentInfoProperties.inn
            this.kpp = companyPaymentInfoProperties.kpp
            this.companyName = companyPaymentInfoProperties.companyName
            this.companyAccountNumber = companyPaymentInfoProperties.companyAccountNumber
            this.bankBik = companyPaymentInfoProperties.bankBik
            this.bankAccountNumber = companyPaymentInfoProperties.bankAccountNumber
            this.bankName = companyPaymentInfoProperties.bankName
            this.purposeOfPayment = companyPaymentInfoProperties.purposeOfPayment
            this.sum = companyPaymentInfoProperties.sum
        }.also {
            runCatching {
                companyPaymentInfoService.save(it)
            }.let {
                if(it.isFailure){
                    log.error(it.exceptionOrNull()?.message)
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<ConsumerServerApplication>(*args)
}
