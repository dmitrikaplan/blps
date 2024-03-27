package ru.kaplaan.api.domain.exceptionHandler

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import ru.kaplaan.api.domain.exception.BadResponseException

@RestControllerAdvice
class ResponseExceptionHandler {


    @ExceptionHandler(BadResponseException::class)
    fun emptyBodyExceptionHandler(e: BadResponseException): Mono<ServerResponse> {
        return ServerResponse.ok().body(e.response)
    }

}