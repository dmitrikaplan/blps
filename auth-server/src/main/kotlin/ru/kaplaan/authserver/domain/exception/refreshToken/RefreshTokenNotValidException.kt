package ru.kaplaan.authserver.domain.exception.refreshToken

class RefreshTokenNotValidException: RefreshTokenException("Не валидный refresh token!") {

    override val message: String
        get() = super.message
}