package ru.kaplaan.api.web.controller.consumerServer.payment

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.payment.PaymentInfoService
import ru.kaplaan.api.web.dto.consumerServer.payment.PaymentInfoDto
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate

@RestController
@RequestMapping("/api/v1/consumer/payment")
@Tag(name = "Payment Controller", description = "Контроллер взаимодействия с данными для оплаты")
class PaymentController(
    private val paymentInfoService: PaymentInfoService
) {

    @PostMapping("/info")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Добавить платежную информацию компании")
    fun savePaymentInfo(
        @RequestBody @Validated(OnCreate::class)
        paymentInfoDto: Mono<PaymentInfoDto>,
        authentication: Authentication
    ): Mono<PaymentInfoDto> = paymentInfoService.savePaymentInfo(
        paymentInfoDto.map {
            it.apply {
                this.companyId = (authentication.details as String).toLong()
            }
        }
    )

    @PutMapping("/info")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Обновить платежную информацию компании")
    fun updatePaymentInfo(
        @RequestBody @Validated(OnUpdate::class)
        paymentInfoDto: Mono<PaymentInfoDto>,
        authentication: Authentication
    ): Mono<PaymentInfoDto> = paymentInfoService.updatePaymentInfo(
        paymentInfoDto.map {
            it.apply {
                this.companyId = (authentication.details as String).toLong()
            }
        }
    )

    @GetMapping("/info")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Получить платежную информацию компании")
    fun getPaymentInfo(
        authentication: Authentication
    ): Mono<PaymentInfoDto> = paymentInfoService.getPaymentInfoByCompanyId((authentication.details as String).toLong())


}