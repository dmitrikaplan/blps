package ru.kaplaan.consumer.service.accountant.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.service.accountant.AccountantService
import ru.kaplaan.consumer.service.email.EmailService
import ru.kaplaan.consumer.service.paymentOrder.PaymentOrderService

@Service
class AccountantServiceImpl(
    private val paymentOrderService: PaymentOrderService,
    private val emailService: EmailService,
): AccountantService {
    override fun sendPaymentOrder(companyId: Long) {

    }
}