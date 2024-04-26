package ru.kaplaan.mailserver.web.listener

import kotlinx.serialization.json.Json
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.kaplaan.mailserver.service.EmailService
import ru.kaplaan.mailserver.web.dto.paymentOrder.PaymentOrderEmailDto

//@Component
//@EnableRabbit
//class PaymentOrderListener(
//    private val emailService: EmailService
//) {
//
//    @RabbitListener(queues = ["send-payment-order-mail-queue"])
//    fun sendPaymentOrderMail(json: String){
//        Json.decodeFromString<PaymentOrderEmailDto>(json).let { paymentOrderEmailDto ->
//            emailService.sendPaymentOrder(paymentOrderEmailDto)
//        }
//    }
//}