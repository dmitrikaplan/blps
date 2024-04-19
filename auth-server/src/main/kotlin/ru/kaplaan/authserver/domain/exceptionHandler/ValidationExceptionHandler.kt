package ru.kaplaan.authserver.domain.exceptionHandler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidationExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(BindException::class)
    fun bindExceptionHandler(bindException: BindException): ResponseEntity<ProblemDetail>{
        bindException.allErrors.forEach {
            log.debug(it.defaultMessage)
        }
        return ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, bindException.message)
            .apply {
                setProperty("errors", bindException.allErrors.map { it.defaultMessage })
            }
            .let {
                ResponseEntity.status(it.status).body(it)
            }
    }
}