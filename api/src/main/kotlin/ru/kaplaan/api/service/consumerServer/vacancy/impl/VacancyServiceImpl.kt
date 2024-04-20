package ru.kaplaan.api.service.consumerServer.vacancy.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.body
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.vacancy.VacancyService
import ru.kaplaan.api.web.dto.consumerServer.vacancy.ArchiveVacancyDto
import ru.kaplaan.api.web.dto.consumerServer.vacancy.VacancyDto

@Service
class VacancyServiceImpl(
    private val webClient: WebClient
): VacancyService {

    @Value("\${consumer-server.base-url}")
    private lateinit var baseUrl: String

    @Value("\${consumer-server.vacancy.url}")
    private lateinit var url: String

    @Value("\${consumer-server.vacancy.save}")
    private lateinit var saveEndpoint: String

    @Value("\${consumer-server.vacancy.update}")
    private lateinit var updateEndpoint: String

    @Value("\${consumer-server.vacancy.delete}")
    private lateinit var deleteEndpoint: String

    @Value("\${consumer-server.vacancy.get-vacancy-by-id}")
    private lateinit var getVacancyByIdEndpoint: String

    @Value("\${consumer-server.vacancy.get-vacancies-by-company-id}")
    private lateinit var getVacanciesByCompanyIdEndpoint: String

    @Value("\${consumer-server.vacancy.get-vacancies}")
    private lateinit var getVacanciesEndpoint: String

    @Value("\${consumer-server.vacancy.get-vacancies-by-text}")
    private lateinit var getVacanciesByTextEndpoint: String

    @Value("\${consumer-server.vacancy.archive-vacancy}")
    lateinit var archiveVacancyEndpoint: String

    @Value("\${consumer-server.vacancy.unarchive-vacancy}")
    lateinit var unarchiveVacancyEndpoint: String

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

    override fun delete(companyId: Long, vacancyId: Long): Mono<ResponseEntity<Any>> =
        webClient
            .delete()
            .uri("$baseUrl$url$deleteEndpoint$companyId/$vacancyId")
            .retrieve()
            .toEntity(Any::class.java)

    override fun getVacancyById(vacancyId: Long): Mono<ResponseEntity<VacancyDto>> =
        webClient
            .get()
            .uri("$baseUrl$url$getVacancyByIdEndpoint/$vacancyId")
            .retrieve()
            .toEntity(VacancyDto::class.java)

    override fun getVacanciesByCompanyId(companyId: Long, pageNumber: Int): Mono<ResponseEntity<Flux<VacancyDto>>> =
        webClient
            .get()
            .uri("$baseUrl$url$getVacanciesByCompanyIdEndpoint/$companyId/$pageNumber")
            .retrieve()
            .toEntityFlux(VacancyDto::class.java)

    override fun getVacancies(pageNumber: Int): Mono<ResponseEntity<Flux<VacancyDto>>> =
        webClient
            .get()
            .uri("$baseUrl$url$getVacanciesEndpoint$pageNumber")
            .retrieve()
            .toEntityFlux(VacancyDto::class.java)

    override fun getVacanciesByText(text: String, pageNumber: Int): Mono<ResponseEntity<Flux<VacancyDto>>> =
        webClient
            .get()
            .uri("$baseUrl$url$getVacanciesByTextEndpoint/$text/$pageNumber")
            .retrieve()
            .toEntityFlux(VacancyDto::class.java)

    override fun archiveVacancy(archiveVacancyDto: Mono<ArchiveVacancyDto>): Mono<ResponseEntity<Any>> =
        webClient
            .post()
            .uri("$baseUrl$url$archiveVacancyEndpoint")
            .body(archiveVacancyDto)
            .retrieve()
            .toEntity(Any::class.java)

    override fun unarchiveVacancy(archiveVacancyDto: Mono<ArchiveVacancyDto>): Mono<ResponseEntity<Any>> =
        webClient
            .post()
            .uri("$baseUrl$url$unarchiveVacancyEndpoint")
            .body(archiveVacancyDto)
            .retrieve()
            .toEntity(Any::class.java)
}