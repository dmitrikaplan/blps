package ru.kaplaan.vacancy.domain.exceptionHandler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.kaplaan.vacancy.domain.not_found.NotFoundException

@RestControllerAdvice
class DetailsExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(NotFoundException::class)
    fun detailsNotFoundExceptionHandler(exception: NotFoundException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.NOT_FOUND, exception.message)
            .apply {
                setProperty("errors", exception.message)
            }
            .also {
                log.debug(exception.message)
            }
            .let {
                ResponseEntity.badRequest().body(it)
            }
}