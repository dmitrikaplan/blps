package ru.kaplaan.consumer.service.payment

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.payment.CompanyPaymentInfo

@Service
interface CompanyPaymentInfoService {

    fun save(companyPaymentInfo: CompanyPaymentInfo): CompanyPaymentInfo

    fun get(): CompanyPaymentInfo
}