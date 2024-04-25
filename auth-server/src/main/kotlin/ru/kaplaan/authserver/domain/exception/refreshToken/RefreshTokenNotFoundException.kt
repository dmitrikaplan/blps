package ru.kaplaan.authserver.domain.exception.refreshToken

class RefreshTokenNotFoundException: RefreshTokenException("Refresh token не найден!") {

    override val message: String
        get() = super.message
}