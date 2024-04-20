package ru.kaplaan.api.service.authServer

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
interface UserInfoService {
    fun getUsernameByUserId(userId: Long): Mono<ResponseEntity<String>>
}