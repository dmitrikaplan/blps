package ru.kaplaan.authserver.service.impl

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kaplaan.authserver.domain.entity.user.User
import ru.kaplaan.authserver.domain.exception.user.UserAlreadyRegisteredException
import ru.kaplaan.authserver.domain.user.UserIdentification
import ru.kaplaan.authserver.service.*
import ru.kaplaan.authserver.web.dto.response.JwtResponse
import java.util.*

@Service
class AuthServiceImpl(
    private val userService: UserService,
    private val refreshTokenService: RefreshTokenService,
    private val emailService: EmailService,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
) : AuthService {

    @Transactional
    override fun register(user: User) {

        checkRegistration(user)

        val activationCode = UUID.randomUUID().toString().replace("-", "")

        user.apply {
            this.password = passwordEncoder.encode(user.password)
            this.activationCode = activationCode
        }

        userService.save(user)
        emailService.activateUserByEmail(user.email, user.username, activationCode)
    }

    @Transactional
    override fun authenticate(userIdentification: UserIdentification): JwtResponse {
        val user = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken
                .unauthenticated(userIdentification.usernameOrEmail, userIdentification.password)
        ).principal as User

        val accessToken = jwtService.generateJwtAccessToken(user)
        val refreshToken = refreshTokenService.getRefreshTokenByUser(user)

        return JwtResponse(accessToken, refreshToken)
    }

    @Transactional
    override fun authenticate(authentication: Authentication): Authentication =
        authenticationManager.authenticate(authentication)


    @Transactional
    override fun activateAccount(activationCode: String) {
        userService.getUserByActivationCode(activationCode).apply {
            this.activationCode = null
            this.activated = true
            userService.save(this)
        }

    }

    override fun passwordRecovery(userIdentification: UserIdentification) {
        TODO("Полностью переделать восстановление пароля")
    }

    @Transactional
    override fun refresh(token: String): JwtResponse {

        refreshTokenService.validateRefreshToken(token)

        val accessToken = refreshTokenService.findRefreshTokenByToken(token).let {
            val user = userService.getUserById(it.userId!!)
            jwtService.generateJwtAccessToken(user)
        }

        return JwtResponse(accessToken, token)
    }

    private fun checkRegistration(user: User) =
        userService.getUserByUsername(user.username)?.let {
            throw UserAlreadyRegisteredException("Пользователь с таким логином или паролем уже существует")
        }
}