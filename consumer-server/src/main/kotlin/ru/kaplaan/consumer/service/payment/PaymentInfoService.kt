package ru.kaplaan.consumer.service.payment

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.payment.PaymentInfo

@Service
interface PaymentInfoService {

    fun save(paymentInfo: PaymentInfo): PaymentInfo

    fun update(paymentInfo: PaymentInfo): PaymentInfo

    fun getByCompanyId(companyId: Long): PaymentInfo

    fun existsByCompanyId(companyId: Long): Boolean
}