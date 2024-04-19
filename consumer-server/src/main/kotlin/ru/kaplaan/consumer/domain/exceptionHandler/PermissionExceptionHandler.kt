package ru.kaplaan.consumer.domain.exceptionHandler

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.kaplaan.consumer.domain.exception.PermissionDeniedException

@RestControllerAdvice
class PermissionExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)
    @ExceptionHandler(PermissionDeniedException::class)
    fun permissionDeniedExceptionHandler(e: PermissionDeniedException): ResponseEntity<ProblemDetail>{
        return ProblemDetail
            .forStatusAndDetail(HttpStatus.FORBIDDEN, e.message)
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
}