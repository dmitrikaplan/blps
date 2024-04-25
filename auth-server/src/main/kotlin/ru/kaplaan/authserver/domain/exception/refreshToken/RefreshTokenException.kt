package ru.kaplaan.authserver.domain.exception.refreshToken

abstract class RefreshTokenException(override val message: String)
    : RuntimeException(message)