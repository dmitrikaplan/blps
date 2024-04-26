package ru.kaplaan.consumer.web.mapper.payment

import ru.kaplaan.consumer.domain.entity.payment.CompanyPaymentInfo
import ru.kaplaan.consumer.domain.entity.payment.PaymentInfo
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder
import ru.kaplaan.consumer.web.dto.payment.PaymentOrderDto
import java.time.LocalDate

fun PaymentOrderDto.toEntity(): PaymentOrder =
    PaymentOrder().apply {
        id = this@toEntity.id
        payerInn = this@toEntity.payerInn
        payerKpp = this@toEntity.payerKpp
        payerCompanyName = this@toEntity.payerCompanyName
        payerCompanyAccountNumber = this@toEntity.payerCompanyAccountNumber
        payerBankBik = this@toEntity.payerBankBik
        payerBankAccountNumber = this@toEntity.payerBankAccountNumber
        payerBankName = this@toEntity.payerBankName
        payerCompanyId = this@toEntity.payerCompanyId

        recipientInn = this@toEntity.recipientInn
        recipientKpp = this@toEntity.recipientKpp
        recipientCompanyName = this@toEntity.recipientCompanyName
        recipientCompanyAccountNumber = this@toEntity.recipientCompanyAccountNumber
        recipientBankBik = this@toEntity.recipientBankBik
        recipientBankAccountNumber = this@toEntity.recipientBankAccountNumber
        recipientBankName = this@toEntity.recipientBankName
        creationDate = this@toEntity.creationDate
        isCompleted = this@toEntity.isCompleted
    }


fun PaymentOrder.toDto(): PaymentOrderDto =
    PaymentOrderDto(
        payerInn = payerInn,
        payerKpp = payerKpp,
        payerCompanyName = payerCompanyName,
        payerCompanyAccountNumber = payerCompanyAccountNumber,
        payerBankBik = payerBankBik,
        payerBankAccountNumber = payerBankAccountNumber,
        payerBankName = payerBankName,
        payerCompanyId = payerCompanyId!!,

        recipientInn = recipientInn,
        recipientKpp = recipientKpp,
        recipientCompanyName = recipientCompanyName,
        recipientCompanyAccountNumber = recipientCompanyAccountNumber,
        recipientBankBik = recipientBankBik,
        recipientBankAccountNumber = recipientBankAccountNumber,
        recipientBankName = recipientBankName,
        creationDate = creationDate,
        isCompleted = isCompleted!!
    ).apply {
        this.id = this@toDto.id
    }


fun Pair<PaymentInfo, CompanyPaymentInfo>.createPaymentOrder(): PaymentOrder {
    val payer = this.first
    val recipient = this.second
    return PaymentOrder().apply {
        payerInn = payer.inn
        payerKpp = payer.kpp
        payerCompanyName = payer.companyName
        payerCompanyAccountNumber = payer.companyAccountNumber
        payerBankBik = payer.bankBik
        payerBankAccountNumber = payer.bankAccountNumber
        payerBankName = payer.bankName
        payerCompanyId = payer.companyId

        recipientInn = recipient.inn
        recipientKpp = recipient.kpp
        recipientCompanyName = recipient.companyName
        recipientCompanyAccountNumber = recipient.companyAccountNumber
        recipientBankBik = recipient.bankBik
        recipientBankAccountNumber = recipient.bankAccountNumber
        recipientBankName = recipient.bankName
        creationDate = LocalDate.now()
        isCompleted = false
    }
}




fun List<PaymentOrder>.toDto(): List<PaymentOrderDto> =
    this.map { it.toDto() }