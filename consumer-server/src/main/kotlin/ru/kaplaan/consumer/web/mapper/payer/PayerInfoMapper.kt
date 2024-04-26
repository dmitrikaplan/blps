package ru.kaplaan.consumer.web.mapper.payer

import ru.kaplaan.consumer.domain.entity.payment.PaymentInfo
import ru.kaplaan.consumer.web.dto.payment.PaymentInfoDto


fun PaymentInfoDto.toEntity(): PaymentInfo =
    PaymentInfo().apply {
        this.id = this@toEntity.id
        this.inn = this@toEntity.inn
        this.kpp = this@toEntity.kpp
        this.payerAccountNumber = this@toEntity.payerAccountNumber
        this.bankBik = this@toEntity.bankBik
        this.bankAccountNumber = this@toEntity.bankAccountNumber
        this.companyId = this@toEntity.companyId
    }



fun PaymentInfo.toDto(): PaymentInfoDto =
    PaymentInfoDto(
        inn = this.inn,
        kpp = this.kpp,
        payerAccountNumber = this.payerAccountNumber,
        bankBik = this.bankBik,
        bankAccountNumber = this.bankAccountNumber,
        companyId = this.companyId!!
    ).apply {
        this.id = this@toDto.id
    }