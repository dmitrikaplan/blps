package ru.kaplaan.consumer.service.payment

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder

@Service
interface PaymentOrderService {


    fun generatePaymentOrder(companyId: Long, countOfPayments: Long = 1): PaymentOrder

    fun getPaymentOrdersByCompanyId(companyId: Long, pageNumber: Int): List<PaymentOrder>

    fun getPaymentOrderById(id: Long): PaymentOrder

    fun setPaymentOrderCompleted(paymentOrderId: Long)

}