package ru.kaplaan.consumer.web.controller.payment

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/consumer/payment")
class PaymentOrderController {


    @PostMapping
    fun saveCompanyPayment(){

    }
}