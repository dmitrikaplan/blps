package ru.kaplaan.consumer.service.paymentOrder.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.repository.payment.PaymentOrderRepository
import ru.kaplaan.consumer.service.paymentOrder.PaymentOrderService

@Service
class PaymentOrderServiceImpl(
    private val paymentOrderRepository: PaymentOrderRepository
): PaymentOrderService {
    override fun generatePaymentOrder() {
        TODO("Not yet implemented")
    }


}