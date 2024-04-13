package ru.kaplaan.vacancy.domain.exceptionHandler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.kaplaan.vacancy.domain.exception.alreadyExists.AlreadyExistsException
import ru.kaplaan.vacancy.domain.exception.notFound.NotFoundException

@RestControllerAdvice
class DetailsExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(NotFoundException::class)
    fun notFoundExceptionHandler(e: NotFoundException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.NOT_FOUND, e.message)
            .apply {
                setProperty("errors", e.message)
            }
            .also {
                log.debug(e.message)
            }
            .let {
                ResponseEntity.badRequest().body(it)
            }


    @ExceptionHandler(AlreadyExistsException::class)
    fun alreadyExistsExceptionHandler(e: AlreadyExistsException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, e.message)
            .apply {
                setProperty("errors", e.message)
            }
            .also {
                log.debug(e.message)
            }
            .let {
                ResponseEntity.badRequest().body(it)
            }
}