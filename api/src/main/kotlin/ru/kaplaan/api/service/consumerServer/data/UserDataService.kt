package ru.kaplaan.api.service.consumerServer.data

import org.springframework.http.ResponseEntity
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.consumerServer.data.UserDataDto

interface UserDataService {
    fun saveUserData(userDataDto: Mono<UserDataDto>): Mono<ResponseEntity<UserDataDto>>

    fun updateUserData(userDataDto: Mono<UserDataDto>): Mono<ResponseEntity<UserDataDto>>

    fun getUserDataByUserId(userId: Long): Mono<ResponseEntity<UserDataDto>>
}