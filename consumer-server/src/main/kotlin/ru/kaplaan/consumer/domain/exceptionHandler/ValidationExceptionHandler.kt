package ru.kaplaan.consumer.domain.exceptionHandler

import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidationExceptionHandler {

    @ExceptionHandler(BindException::class)
    fun bindExceptionHandler(e: BindException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, e.message)
            .apply {
                setProperty("errors", e.message)
            }
            .let{
                ResponseEntity.status(it.status).body(it)
            }
}