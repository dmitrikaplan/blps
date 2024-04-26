package ru.kaplaan.consumer.web.dto.payment

data  class PaymentOrderDto(
    val payerInn: String,
    val payerKpp: String,
    val payerCompanyName: String,
    val payerCompanyAccountNumber: String,
    val payerBankBik: String,
    val payerBankAccountNumber: String,
    val payerBankName: String,
    val payerCompanyId: Long,
    val recipientInn: String,
    val recipientKpp: String,
    val recipientCompanyName: String,
    val recipientCompanyAccountNumber: String,
    val recipientBankBik: String,
    val recipientBankAccountNumber: String,
    val recipientBankName: String
)