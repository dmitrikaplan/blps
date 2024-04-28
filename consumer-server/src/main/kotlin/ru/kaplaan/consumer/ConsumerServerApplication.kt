package ru.kaplaan.consumer

import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.kaplaan.consumer.domain.entity.payment.CompanyPaymentInfo
import ru.kaplaan.consumer.service.payment.CompanyPaymentInfoService

@SpringBootApplication
class ConsumerServerApplication(
    private val companyPaymentInfoService: CompanyPaymentInfoService
){

    @Value("\${company-payment-info.inn}")
    private lateinit var inn: String

    @Value("\${company-payment-info.kpp}")
    private lateinit var kpp: String

    @Value("\${company-payment-info.company-name}")
    private lateinit var companyName: String

    @Value("\${company-payment-info.company-account-number}")
    private lateinit var companyAccountNumber: String

    @Value("\${company-payment-info.bank-bik}")
    private lateinit var bankBik: String

    @Value("\${company-payment-info.bank-account-number}")
    private lateinit var bankAccountNumber: String

    @Value("\${company-payment-info.bank-name}")
    private lateinit var bankName: String

    @Value("\${company-payment-info.purpose-of-payment}")
    private lateinit var purposeOfPayment: String

    @Value("\${company-payment-info.sum}")
    private var sum: Long? = null

    private val log = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    fun initCompanyPaymentInfo(){
        CompanyPaymentInfo().apply {
            this.inn = this@ConsumerServerApplication.inn
            this.kpp = this@ConsumerServerApplication.kpp
            this.companyName = this@ConsumerServerApplication.companyName
            this.companyAccountNumber = this@ConsumerServerApplication.companyAccountNumber
            this.bankBik = this@ConsumerServerApplication.bankBik
            this.bankAccountNumber = this@ConsumerServerApplication.bankAccountNumber
            this.bankName = this@ConsumerServerApplication.bankName
            this.purposeOfPayment = this@ConsumerServerApplication.purposeOfPayment
            this.sum = this@ConsumerServerApplication.sum!!
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
