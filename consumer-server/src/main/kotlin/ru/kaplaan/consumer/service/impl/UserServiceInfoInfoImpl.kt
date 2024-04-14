package ru.kaplaan.consumer.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import ru.kaplaan.consumer.domain.exception.BodilessResponseException
import ru.kaplaan.consumer.service.UserServiceInfo

@Service
class UserServiceInfoInfoImpl(
    private val restClient: RestClient
): UserServiceInfo {

    @Value("\${auth-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${auth-server.endpoints.get-all-user-id-by-usernames}")
    lateinit var getAllUsernamesByUserIdsEndpoint: String

    @Value("\${auth-server.endpoints.get-user-id-by-username}")
    lateinit var getUserIdByUsernameEndpoint: String

    @Value("\${auth-server.endpoints.get-username-by-user-id}")
    lateinit var getUsernameByUserIdEndpoint: String

    override fun getUserIdByUsername(username: String): Long {
        return restClient
            .get()
            .uri("$baseUrl$getUserIdByUsernameEndpoint/$username")
            .retrieve()
            .body(Long::class.java) ?: throw BodilessResponseException()
    }

    override fun getUsernameByUserId(userId: Long): String {
        return restClient
            .get()
            .uri("$baseUrl$getUsernameByUserIdEndpoint/$userId")
            .retrieve()
            .body(String::class.java) ?: throw BodilessResponseException()
    }

    override fun getAllUsernamesByUserIds(id: List<Long>): List<String> {
        return restClient
            .post()
            .uri("$baseUrl$getAllUsernamesByUserIdsEndpoint")
            .body(id)
            .retrieve()
            .body(object: ParameterizedTypeReference<List<String>>(){}) ?: throw BodilessResponseException()
    }
}