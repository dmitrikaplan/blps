package ru.kaplaan.vacancy.domain.advice

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.kaplaan.vacancy.domain.not_found.NotFoundException

@ControllerAdvice
class DetailsControllerAdvice {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(NotFoundException::class)
    fun detailsNotFoundExceptionHandler(exception: NotFoundException): ResponseEntity<ProblemDetail> {
        ProblemDetail
            .forStatusAndDetail(HttpStatus.NOT_FOUND, exception.message)
            .apply {
                setProperty("errors", exception.message)
                log.debug(exception.message)
                return ResponseEntity.badRequest().body(this)
            }
    }
}