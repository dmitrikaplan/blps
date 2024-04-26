package ru.kaplaan.consumer.web.mapper.payment

import ru.kaplaan.consumer.domain.entity.payment.PaymentInfo
import ru.kaplaan.consumer.web.dto.payment.PaymentInfoDto


fun PaymentInfoDto.toEntity(): PaymentInfo =
    PaymentInfo().apply {
        this.id = this@toEntity.id
        this.inn = this@toEntity.inn
        this.kpp = this@toEntity.kpp
        this.companyName = this@toEntity.companyName
        this.companyAccountNumber = this@toEntity.companyAccountNumber
        this.bankBik = this@toEntity.bankBik
        this.bankAccountNumber = this@toEntity.bankAccountNumber
        this.bankName = this@toEntity.bankName
        this.companyId = this@toEntity.companyId
    }



fun PaymentInfo.toDto(): PaymentInfoDto =
    PaymentInfoDto(
        inn = this.inn,
        kpp = this.kpp,
        companyAccountNumber = this.companyAccountNumber,
        companyName = this.companyName,
        bankBik = this.bankBik,
        bankAccountNumber = this.bankAccountNumber,
        bankName = this.bankName,
        companyId = this.companyId!!
    ).apply {
        this.id = this@toDto.id
    }