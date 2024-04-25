package ru.kaplaan.authserver.service

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.entity.refreshToken.RefreshToken
import ru.kaplaan.authserver.domain.entity.user.User

@Service
interface RefreshTokenService {

    fun findRefreshTokenByToken(token: String): RefreshToken

    fun validateRefreshToken(token: String)

    fun getRefreshTokenByUser(user: User): String
}