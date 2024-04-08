package ru.kaplaan.api.service.consumerServer.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.VacancyService
import ru.kaplaan.api.web.dto.consumerServer.vacancy.VacancyDto

@Service
class VacancyServiceImpl(
    private val webClient: WebClient
): VacancyService {

    @Value("\${consumer-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${consumer-server.vacancy.url}")
    lateinit var url: String

    @Value("\${consumer-server.vacancy.save}")
    lateinit var saveEndpoint: String

    @Value("\${consumer-server.vacancy.update}")
    lateinit var updateEndpoint: String

    @Value("\${consumer-server.vacancy.delete}")
    lateinit var deleteEndpoint: String

    @Value("\${consumer-server.vacancy.get-vacancy-by-id}")
    lateinit var getVacancyByIdEndpoint: String

    @Value("\${consumer-server.vacancy.get-vacancies-by-company-id}")
    lateinit var getVacanciesByCompanyIdEndpoint: String

    override fun save(vacancyDto: Mono<VacancyDto>): Mono<ResponseEntity<VacancyDto>> =
        webClient
            .post()
            .uri("$baseUrl$url$saveEndpoint")
            .body(vacancyDto)
            .retrieve()
            .toEntity(VacancyDto::class.java)

    override fun update(vacancyDto: Mono<VacancyDto>): Mono<ResponseEntity<VacancyDto>> =
        webClient
            .put()
            .uri("$baseUrl$url$updateEndpoint")
            .body(vacancyDto)
            .retrieve()
            .toEntity(VacancyDto::class.java)

    override fun delete(companyName: String, vacancyId: Long): Mono<ResponseEntity<Any>> =
        webClient
            .delete()
            .uri("$baseUrl$url$deleteEndpoint$companyName/$vacancyId")
            .retrieve()
            .toEntity(Any::class.java)

    override fun getVacancyById(vacancyId: Long): Mono<ResponseEntity<VacancyDto>> =
        webClient
            .get()
            .uri("$baseUrl$url$getVacancyByIdEndpoint$vacancyId")
            .retrieve()
            .toEntity(VacancyDto::class.java)

    override fun getVacanciesByCompanyName(companyName: String, pageNumber: Int): Mono<ResponseEntity<Flux<VacancyDto>>> =
        webClient
            .get()
            .uri("$baseUrl$url$getVacanciesByCompanyIdEndpoint$companyName/$pageNumber")
            .retrieve()
            .toEntityFlux(VacancyDto::class.java)
}