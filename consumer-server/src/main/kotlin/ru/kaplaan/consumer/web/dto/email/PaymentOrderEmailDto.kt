package ru.kaplaan.consumer.web.dto.email

import java.time.LocalDate

data class PaymentOrderEmailDto(
    val payerInn: String,
    val payerKpp: String,
    val payerCompanyName: String,
    val payerCompanyAccountNumber: String,
    val payerBankBik: String,
    val payerBankAccountNumber: String,
    val payerBankName: String,
    val recipientInn: String,
    val recipientKpp: String,
    val recipientCompanyName: String,
    val recipientCompanyAccountNumber: String,
    val recipientBankBik: String,
    val recipientBankAccountNumber: String,
    val recipientBankName: String,
    val creationDate: LocalDate,
    val sum : Long,
    val email: String,
    val purposeOfPayment: String
)