package ru.kaplaan.api.service.consumerServer.payment

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.consumerServer.payment.PaymentInfoDto

@Service
interface PaymentInfoService {


    fun savePaymentInfo(paymentInfoDto: Mono<PaymentInfoDto>): Mono<PaymentInfoDto>

    fun updatePaymentInfo(paymentInfoDto: Mono<PaymentInfoDto>): Mono<PaymentInfoDto>

    fun getPaymentInfoByCompanyId(companyId: Long): Mono<PaymentInfoDto>
}