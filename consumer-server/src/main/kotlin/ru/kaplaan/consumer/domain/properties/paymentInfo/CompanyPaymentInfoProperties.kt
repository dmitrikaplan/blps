package ru.kaplaan.consumer.domain.properties.paymentInfo

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "company-payment-info")
data class CompanyPaymentInfoProperties @ConstructorBinding constructor(
    val inn: String,
    val kpp: String,
    val companyName: String,
    val companyAccountNumber: String,
    val bankBik: String,
    val bankAccountNumber: String,
    val bankName: String,
    val purposeOfPayment: String,
    val sum: Long,
)