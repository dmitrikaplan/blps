package ru.kaplaan.api.service.consumerServer.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.body
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.DataService
import ru.kaplaan.api.web.dto.consumerServer.data.CompanyDataDto
import ru.kaplaan.api.web.dto.consumerServer.data.UserDataDto


@Service
class DataServiceImpl(
    private val webClient: WebClient
): DataService {

    @Value("\${consumer-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${consumer-server.data.url}")
    lateinit var url: String

    @Value("\${consumer-server.data.save-company-data}")
    lateinit var saveCompanyDataEndpoint: String

    @Value("\${consumer-server.data.update-company-data}")
    lateinit var updateCompanyDataEndpoint: String

    @Value("\${consumer-server.data.get-company-data}")
    lateinit var getCompanyDataEndpoint: String

    @Value("\${consumer-server.data.save-user-data}")
    lateinit var saveUserDataEndpoint: String

    @Value("\${consumer-server.data.update-user-data}")
    lateinit var updateUserDataEndpoint: String

    @Value("\${consumer-server.data.get-user-data}")
    lateinit var getUserDataEndpoint: String

    
    override fun saveCompanyData(companyDataDto: Mono<CompanyDataDto>): Mono<ResponseEntity<CompanyDataDto>> =
        webClient
            .post()
            .uri("$baseUrl$url$saveCompanyDataEndpoint")
            .body(companyDataDto)
            .retrieve()
            .toEntity(CompanyDataDto::class.java)

    override fun updateCompanyData(companyDataDto: Mono<CompanyDataDto>): Mono<ResponseEntity<CompanyDataDto>> =
        webClient
            .put()
            .uri("$baseUrl$url$updateCompanyDataEndpoint")
            .body(companyDataDto)
            .retrieve()
            .toEntity(CompanyDataDto::class.java)

    override fun getCompanyDataByCompanyName(companyName: String): Mono<ResponseEntity<CompanyDataDto>> =
        webClient
            .get()
            .uri("$baseUrl$url$getCompanyDataEndpoint/$companyName")
            .retrieve()
            .toEntity(CompanyDataDto::class.java)

    override fun saveUserData(userDataDto: Mono<UserDataDto>): Mono<ResponseEntity<UserDataDto>> =
        webClient
            .post()
            .uri("$baseUrl$url$saveUserDataEndpoint")
            .body(userDataDto)
            .retrieve()
            .toEntity(UserDataDto::class.java)

    override fun updateUserData(userDataDto: Mono<UserDataDto>): Mono<ResponseEntity<UserDataDto>> =
        webClient
            .put()
            .uri("$baseUrl$url$updateUserDataEndpoint")
            .body(userDataDto)
            .retrieve()
            .toEntity(UserDataDto::class.java)

    override fun getUserDataByUsername(username: String): Mono<ResponseEntity<UserDataDto>> =
        webClient
            .get()
            .uri("$baseUrl$url$getUserDataEndpoint/$username")
            .retrieve()
            .toEntity(UserDataDto::class.java)
}