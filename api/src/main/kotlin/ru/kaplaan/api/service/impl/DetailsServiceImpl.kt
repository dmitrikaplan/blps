package ru.kaplaan.api.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.body
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.DetailsService
import ru.kaplaan.api.web.dto.details.CompanyDetailsDto
import ru.kaplaan.api.web.dto.details.UserDetailsDto


@Service
class DetailsServiceImpl(
    private val webClient: WebClient
): DetailsService {

    @Value("\${vacancy-server.details.base-url}")
    lateinit var baseUrl: String

    @Value("\${vacancy-server.details.save-company-details}")
    lateinit var saveCompanyDetailsEndpoint: String

    @Value("\${vacancy-server.details.get-company-details}")
    lateinit var getCompanyDetailsEndpoint: String

    @Value("\${vacancy-server.details.save-user-details}")
    lateinit var saveUserDetailsEndpoint: String

    @Value("\${vacancy-server.details.get-user-details}")
    lateinit var getUserDetailsEndpoint: String


    override fun saveCompanyDetails(companyDetailsDto: Mono<CompanyDetailsDto>): Mono<ResponseEntity<String>> =
        webClient
            .post()
            .uri("$baseUrl$saveCompanyDetailsEndpoint")
            .body(companyDetailsDto)
            .retrieve()
            .toEntity(String::class.java)

    override fun getCompanyDetailsByCompanyName(companyName: String): Mono<ResponseEntity<CompanyDetailsDto>> =
        webClient
            .get()
            .uri("$baseUrl$getCompanyDetailsEndpoint$companyName")
            .retrieve()
            .toEntity(CompanyDetailsDto::class.java)

    override fun saveUserDetails(userDetailsDto: Mono<UserDetailsDto>): Mono<ResponseEntity<String>> =
        webClient
            .post()
            .uri("$baseUrl$saveUserDetailsEndpoint")
            .body(userDetailsDto)
            .retrieve()
            .toEntity(String::class.java)

    override fun getUserDetailsByUsername(username: String): Mono<ResponseEntity<UserDetailsDto>> =
        webClient
            .get()
            .uri("$baseUrl$getUserDetailsEndpoint$username")
            .retrieve()
            .toEntity(UserDetailsDto::class.java)
}