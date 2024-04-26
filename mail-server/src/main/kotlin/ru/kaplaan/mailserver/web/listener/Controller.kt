package ru.kaplaan.mailserver.web.listener

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.mailserver.service.EmailService
import ru.kaplaan.mailserver.web.dto.paymentOrder.PaymentOrderEmailDto

@RestController
class Controller(
    private val emailService: EmailService
) {

    @PostMapping("/test")
    fun test(@RequestBody paymentOrderEmailDto: PaymentOrderEmailDto){
        emailService.sendPaymentOrder(paymentOrderEmailDto)
    }
}