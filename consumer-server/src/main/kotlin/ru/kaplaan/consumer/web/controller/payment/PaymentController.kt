package ru.kaplaan.consumer.web.controller.payment

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.consumer.service.payment.PaymentInfoService
import ru.kaplaan.consumer.service.payment.PaymentOrderService
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


    @GetMapping("/order/{companyId}/{pageNumber}")
    fun getPaymentOrderByCompanyId(
        @Validated @Min(0, message = "Минимальное Id компании - 0!")
        @PathVariable companyId: Long,
        @Validated @Min(0, message = "Минимальный номер страницы - 0!")
        @PathVariable pageNumber: Int
    ): List<PaymentOrderDto> = paymentOrderService.getPaymentOrdersByCompanyId(companyId, pageNumber).toDto()


    @PutMapping("/order/{paymentOrderId}")
    fun markPaymentCompleted(
        @Validated @Min(0, message = "Минимальное id платежа - 0!")
        @PathVariable paymentOrderId: Long
    ){
        paymentOrderService.setPaymentOrderCompleted(paymentOrderId)
    }
}