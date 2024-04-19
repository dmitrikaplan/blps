package ru.kaplaan.consumer.domain.exceptionHandler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.kaplaan.consumer.domain.exception.BodilessResponseException

@RestControllerAdvice
class ResponseExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(BodilessResponseException::class)
    fun bodilessResponseExceptionHandler(e: BodilessResponseException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, e.message)
            .apply {
                setProperty("errors", e.message)
            }
            .also {
                log.debug(e.message)
            }
            .let {
                ResponseEntity.status(it.status).body(it)
            }
}