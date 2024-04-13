package ru.kaplaan.vacancy.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import ru.kaplaan.vacancy.domain.exception.BodilessResponseException
import ru.kaplaan.vacancy.service.UserService

@Service
class UserServiceImpl(
    private val restClient: RestClient
): UserService {

    @Value("\${auth-server.base-url}")
    lateinit var baseUrl: String

    @Value("\${auth-server.endpoints.get-user-id-by-username}")
    lateinit var getUserIdByUsernameEndpoint: String

    @Value("\${auth-server.endpoints.get-all-user-id-by-usernames}")
    lateinit var getAllUsernamesByUserIdsEndpoint: String

    override fun getUserIdByUsername(username: String): Long {
        return restClient
            .get()
            .uri("$baseUrl$getUserIdByUsernameEndpoint/$username")
            .retrieve()
            .body(Long::class.java) ?: throw BodilessResponseException()
    }

    override fun getAllUsernamesByUserIds(ids: List<Long>): List<String> {
        return restClient
            .post()
            .uri("$baseUrl$getAllUsernamesByUserIdsEndpoint")
            .body(ids)
            .retrieve()
            .body(List::class.java)?.map {
                it as String
            } ?: throw BodilessResponseException()

        //TODO: добавить параметризированные типы
    }
}