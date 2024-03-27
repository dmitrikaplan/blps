package ru.kaplaan.authserver.web.mapper

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import ru.kaplaan.authserver.domain.entity.user.Role
import ru.kaplaan.authserver.web.dto.authentication.AuthenticationDto


fun Authentication.toDto() =
    AuthenticationDto(
        principal = this.name,
        credentials = this.credentials ?: "",
        authorities = this.authorities.map { it.authority }
    )



fun AuthenticationDto.toUnauthenticatedToken() =
    UsernamePasswordAuthenticationToken.unauthenticated(
        this.principal,
        this.credentials
    )!!


fun AuthenticationDto.toAuthenticatedToken() =
    UsernamePasswordAuthenticationToken.authenticated(
        this.principal,
        this.credentials,
        this.authorities.map { Role.valueOf(it) }
    )!!