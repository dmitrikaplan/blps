package ru.kaplaan.authserver.service

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.entity.user.User
import ru.kaplaan.authserver.domain.user.UserIdentification
import ru.kaplaan.authserver.web.dto.response.JwtResponse

@Service
interface AuthService {

    fun register(user: User)

    fun authenticate(userIdentification: UserIdentification): JwtResponse


    fun authenticate(authentication: Authentication): Authentication

    fun activateAccount(activationCode: String)

    fun passwordRecovery(userIdentification: UserIdentification)

    fun refresh(token: String): JwtResponse
}
