package ru.kaplaan.consumer.web.dto.email

data class SuccessPaymentEmailDto(
    val email: String,
    val companyName: String,
    val paymentOrderId: Long
)