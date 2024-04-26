package ru.kaplaan.api.service.consumerServer.payment.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.body
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.payment.PaymentInfoService
import ru.kaplaan.api.web.dto.consumerServer.payment.PaymentInfoDto

@Service
class PaymentInfoServiceImpl(
    private val webClient: WebClient
): PaymentInfoService {

    @Value("\${consumer-server.base-url}")
    private lateinit var baseUrl: String

    @Value("\${consumer-server.payment.url}")
    private lateinit var url: String

    @Value("\${consumer-server.payment.save-payment-info}")
    private lateinit var savePaymentInfoEndpoint: String

    @Value("\${consumer-server.payment.update-payment-info}")
    private lateinit var updatePaymentInfoEndpoint: String

    @Value("\${consumer-server.payment.get-payment-info-by-company-id}")
    private lateinit var getPaymentInfoByCompanyIdEndpoint: String

    override fun savePaymentInfo(paymentInfoDto: Mono<PaymentInfoDto>): Mono<PaymentInfoDto> =
        webClient
            .post()
            .uri("$baseUrl$url$savePaymentInfoEndpoint")
            .body(paymentInfoDto)
            .retrieve()
            .bodyToMono(PaymentInfoDto::class.java)

    override fun updatePaymentInfo(paymentInfoDto: Mono<PaymentInfoDto>): Mono<PaymentInfoDto> =
        webClient
            .put()
            .uri("$baseUrl$url$updatePaymentInfoEndpoint")
            .body(paymentInfoDto)
            .retrieve()
            .bodyToMono(PaymentInfoDto::class.java)

    override fun getPaymentInfoByCompanyId(companyId: Long): Mono<PaymentInfoDto> =
        webClient
            .get()
            .uri("$baseUrl$url$getPaymentInfoByCompanyIdEndpoint/$companyId")
            .retrieve()
            .bodyToMono(PaymentInfoDto::class.java)
}