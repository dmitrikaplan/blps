package ru.kaplaan.api.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.DetailsService
import ru.kaplaan.api.web.dto.details.CompanyDetailsDto
import ru.kaplaan.api.web.dto.details.UserDetailsDto


@Service
class DetailsServiceImpl(
    private val webClient: WebClient
): DetailsService {

    @Value("\${auth-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${auth-server.endpoint.registration}")
    lateinit var registrationEndpoint: String

    @Value("\${auth-server.endpoint.login}")
    lateinit var loginEndpoint: String

    @Value("\${auth-server.endpoint.activation}")
    lateinit var activationEndpoint: String

    @Value("\${auth-server.endpoint.recovery}")
    lateinit var recoveryEndpoint: String

    @Value("\${auth-server.endpoint.refresh}")
    lateinit var refreshEndpoint: String


    override fun saveCompanyDetails(companyDetailsDto: Mono<CompanyDetailsDto>): Mono<ResponseEntity<String>> {
        TODO("Not yet implemented")
    }

    override fun getCompanyDetailsByCompanyName(companyName: Mono<String>): Mono<ResponseEntity<CompanyDetailsDto>> {
        TODO("Not yet implemented")
    }

    override fun saveUserDetails(userDetailsDto: Mono<UserDetailsDto>): Mono<ResponseEntity<String>> {
        TODO("Not yet implemented")
    }

    override fun getUserDetailsByUsername(username: Mono<String>): Mono<UserDetailsDto> {
        TODO("Not yet implemented")
    }


}