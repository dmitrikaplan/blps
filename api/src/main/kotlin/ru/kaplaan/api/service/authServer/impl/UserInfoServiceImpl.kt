package ru.kaplaan.api.service.authServer.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.authServer.UserInfoService

@Service
class UserInfoServiceImpl(
    private val webClient: WebClient
): UserInfoService {

    @Value("\${auth-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${auth-server.endpoint.get-username-by-user-id}")
    lateinit var getUsernameByUserIdEndpoint: String
    override fun getUsernameByUserId(userId: Long): Mono<ResponseEntity<String>> =
        webClient
            .get()
            .uri("$baseUrl$getUsernameByUserIdEndpoint/$userId")
            .retrieve()
            .toEntity(String::class.java)
}