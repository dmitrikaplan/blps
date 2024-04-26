package ru.kaplaan.consumer.web.controller.payment

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.consumer.service.payment.PaymentInfoService
import ru.kaplaan.consumer.service.paymentOrder.PaymentOrderService
import ru.kaplaan.consumer.web.dto.payment.PaymentInfoDto
import ru.kaplaan.consumer.web.dto.payment.PaymentOrderDto
import ru.kaplaan.consumer.web.mapper.payment.toDto
import ru.kaplaan.consumer.web.mapper.payment.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

@RestController
@RequestMapping("/consumer/payment")
class PaymentController(
    private val paymentInfoService: PaymentInfoService,
    private val paymentOrderService: PaymentOrderService
) {

    @PostMapping("/info")
    fun savePaymentInfo(
        @RequestBody @Validated(OnCreate::class)
        paymentInfoDto: PaymentInfoDto
    ): PaymentInfoDto = paymentInfoService.save(paymentInfoDto.toEntity()).toDto()


    @PutMapping("/info")
    fun updatePaymentInfo(
        @RequestBody @Validated(OnUpdate::class)
        paymentInfoDto: PaymentInfoDto
    ): PaymentInfoDto = paymentInfoService.update(paymentInfoDto.toEntity()).toDto()


    @GetMapping("/info/{companyId}")
    fun getPaymentInfoByCompanyId(
        @PathVariable @Validated @Min(0, message = "Минимальное Id компании - 0!")
        companyId: Long
    ): PaymentInfoDto = paymentInfoService.getByCompanyId(companyId).toDto()


//    @GetMapping("/order/{companyId}/{pageNumber}")
//    fun getPaymentOrderByCompanyId(
//        @PathVariable companyId: Long,
//        @PathVariable pageNumber: Int
//    ): List<PaymentOrderDto> = paymentOrderService.getPaymentOrdersByCompanyId(companyId).toDto()


}