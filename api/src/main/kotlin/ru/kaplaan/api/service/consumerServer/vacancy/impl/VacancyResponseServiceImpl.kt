package ru.kaplaan.api.service.consumerServer.vacancy.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.vacancy.VacancyResponseService
import ru.kaplaan.api.web.dto.consumerServer.vacancy.VacancyResponseDto

@Service
class VacancyResponseServiceImpl(
    private val webClient: WebClient
): VacancyResponseService {

    @Value("\${consumer-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${consumer-server.vacancy-response.url}")
    lateinit var url: String

    @Value("\${consumer-server.vacancy-response.save}")
    lateinit var saveEndpoint: String

    @Value("\${consumer-server.vacancy-response.delete}")
    lateinit var deleteEndpoint: String

    @Value("\${consumer-server.vacancy-response.get-all-user-id-by-company-id}")
    lateinit var getAllUserIdByCompanyIdEndpoint: String

    override fun save(vacancyResponseDto: Mono<VacancyResponseDto>): Mono<VacancyResponseDto> =
        webClient
            .post()
            .uri("$baseUrl$url$saveEndpoint")
            .body(vacancyResponseDto)
            .retrieve()
            .bodyToMono(VacancyResponseDto::class.java)

    override fun delete(vacancyId: Long, userId: Long): Mono<Any> =
        webClient
            .delete()
            .uri("$baseUrl$url$deleteEndpoint/$vacancyId/$userId")
            .retrieve()
            .bodyToMono(Any::class.java)

    override fun getAllUserIdByCompanyId(companyId: Long, vacancyId: Long, pageNumber: Int): Flux<Long> =
        webClient
            .get()
            .uri("$baseUrl$url$getAllUserIdByCompanyIdEndpoint/$companyId/$vacancyId/$pageNumber")
            .retrieve()
            .bodyToFlux(Long::class.java)
}