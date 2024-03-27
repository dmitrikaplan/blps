package ru.kaplaan.authserver.domain.exceptionHandler

import jakarta.mail.MessagingException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.kaplaan.authserver.domain.exception.refresh_token.RefreshTokenException
import ru.kaplaan.authserver.domain.exception.user.UserException


@RestControllerAdvice
class AuthExceptionHandler {

    private val log = LoggerFactory.getLogger(javaClass)


    @ExceptionHandler(UserException::class)
    fun userExceptionHandler(userException: UserException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, userException.message)
            .apply {
                setProperty("errors", userException.message)
            }
            .also {
                log.debug(userException.message)
            }
            .let {
                ResponseEntity.badRequest().body(it)
            }


    @ExceptionHandler(RefreshTokenException::class)
    fun refreshTokenExceptionHandler(refreshTokenException: RefreshTokenException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, refreshTokenException.message)
            .apply {
                setProperty("errors", refreshTokenException.message)
            }
            .also {
                log.debug(refreshTokenException.message)
            }
            .let {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(it)
            }


    @ExceptionHandler(MessagingException::class)
    fun messagingExceptionHandler(messagingException: MessagingException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Ошибка отправки сообщения на почту. Повторите попытку позже")
            .apply {
                setProperty("errors", "Ошибка отправки сообщения на почту. Повторите попытку позже")
            }
            .also {
                log.debug("Ошибка отправки сообщения на почту")
            }
            .let {
                ResponseEntity.badRequest().body(it)
            }


    @ExceptionHandler(AuthenticationException::class)
    fun authenticationExceptionHandler(e: AuthenticationException): ResponseEntity<ProblemDetail> =
        ProblemDetail
            .forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.message ?: "Ошибка аутентификации!")
            .apply {
                setProperty("errors", e.message)
            }
            .also {
                log.debug(e.message)
            }
            .let {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(it)
            }
}