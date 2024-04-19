package ru.kaplaan.api.domain.exceptionHandler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.support.WebExchangeBindException
import reactor.core.publisher.Mono

@RestControllerAdvice
class ValidationExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(WebExchangeBindException::class)
    fun bindExceptionHandler(bindException: WebExchangeBindException): Mono<ResponseEntity<ProblemDetail>> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, bindException.message)
            .apply {
                setProperty("errors", bindException.allErrors.map { it.defaultMessage })
            }
           .also {
               bindException.allErrors.forEach{
                   log.debug(it.defaultMessage)
               }
           }
            .let {
                Mono.just(ResponseEntity.status(it.status).body(it))
            }
}