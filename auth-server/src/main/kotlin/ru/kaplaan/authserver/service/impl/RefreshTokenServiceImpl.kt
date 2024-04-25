package ru.kaplaan.authserver.service.impl

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.entity.refreshToken.RefreshToken
import ru.kaplaan.authserver.domain.entity.user.User
import ru.kaplaan.authserver.domain.exception.refreshToken.RefreshTokenNotFoundException
import ru.kaplaan.authserver.domain.exception.refreshToken.RefreshTokenNotValidException
import ru.kaplaan.authserver.repository.RefreshTokenRepository
import ru.kaplaan.authserver.service.JwtService
import ru.kaplaan.authserver.service.RefreshTokenService

@Service
class RefreshTokenServiceImpl(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val jwtService: JwtService
): RefreshTokenService {
    override fun findRefreshTokenByToken(token: String): RefreshToken =
        refreshTokenRepository.findRefreshTokenByToken(token)
            ?: throw RefreshTokenNotFoundException()

    override fun validateRefreshToken(token: String) {
        if(!jwtService.isValidRefreshToken(token)){
            refreshTokenRepository.deleteByToken(token)
            throw RefreshTokenNotValidException()
        }
    }

    override fun getRefreshTokenByUser(user: User): String{

        val refreshToken = refreshTokenRepository.findRefreshTokenByUserId(user.id!!)
            ?: return saveRefreshToken(user)

        if(!jwtService.isValidRefreshToken(refreshToken.token))
            return updateRefreshToken(refreshToken, user)

        return refreshToken.token
    }

    private fun updateRefreshToken(refreshToken: RefreshToken, userDetails: UserDetails): String {
        val token = jwtService.generateJwtRefreshToken(userDetails as User)
        refreshToken.token = token
        refreshTokenRepository.save(refreshToken)
        return token
    }

    private fun saveRefreshToken(user: User): String {
        val token =  jwtService.generateJwtRefreshToken(user)
        val refreshToken = RefreshToken().apply {
            this.token = token
            this.userId = user.id!!
        }
        refreshTokenRepository.save(refreshToken)
        return token
    }
}