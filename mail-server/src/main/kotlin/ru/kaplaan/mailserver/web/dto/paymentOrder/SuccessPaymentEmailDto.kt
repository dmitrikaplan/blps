package ru.kaplaan.mailserver.web.dto.paymentOrder

import kotlinx.serialization.Serializable

@Serializable
data class SuccessPaymentEmailDto(
    val email: String,
    val companyName: String,
    val paymentOrderId: Long
)