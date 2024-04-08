package ru.kaplaan.api.service.consumerServer.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.body
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.DetailsService
import ru.kaplaan.api.web.dto.consumerServer.details.CompanyDataDto
import ru.kaplaan.api.web.dto.consumerServer.details.UserDataDto


@Service
class DetailsServiceImpl(
    private val webClient: WebClient
): DetailsService {

    @Value("\${consumer-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${consumer-server.details.url}")
    lateinit var url: String

    @Value("\${consumer-server.details.save-company-details}")
    lateinit var saveCompanyDataEndpoint: String

    @Value("\${consumer-server.details.get-company-details}")
    lateinit var getCompanyDataEndpoint: String

    @Value("\${consumer-server.details.save-user-details}")
    lateinit var saveUserDataEndpoint: String

    @Value("\${consumer-server.details.get-user-details}")
    lateinit var getUserDataEndpoint: String

    
    override fun saveCompanyData(companyDataDto: Mono<CompanyDataDto>): Mono<ResponseEntity<String>> =
        webClient
            .post()
            .uri("$baseUrl$url$saveCompanyDataEndpoint")
            .body(companyDataDto)
            .retrieve()
            .toEntity(String::class.java)

    override fun getCompanyDataByCompanyName(companyName: String): Mono<ResponseEntity<CompanyDataDto>> =
        webClient
            .get()
            .uri("$baseUrl$url$getCompanyDataEndpoint$companyName")
            .retrieve()
            .toEntity(CompanyDataDto::class.java)

    override fun saveUserData(userDataDto: Mono<UserDataDto>): Mono<ResponseEntity<String>> =
        webClient
            .post()
            .uri("$baseUrl$url$saveUserDataEndpoint")
            .body(userDataDto)
            .retrieve()
            .toEntity(String::class.java)

    override fun getUserDataByUsername(username: String): Mono<ResponseEntity<UserDataDto>> =
        webClient
            .get()
            .uri("$baseUrl$url$getUserDataEndpoint$username")
            .retrieve()
            .toEntity(UserDataDto::class.java)
}