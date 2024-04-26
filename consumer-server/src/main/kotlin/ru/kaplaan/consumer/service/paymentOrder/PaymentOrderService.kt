package ru.kaplaan.consumer.service.paymentOrder

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder

@Service
interface PaymentOrderService {


    fun generatePaymentOrder()

    fun getPaymentOrdersByCompanyId(companyId: Long): List<PaymentOrder>

}