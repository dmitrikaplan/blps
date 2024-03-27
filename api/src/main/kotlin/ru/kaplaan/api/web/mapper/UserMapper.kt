package ru.kaplaan.api.web.mapper

import ru.kaplaan.api.domain.user.Role
import ru.kaplaan.api.domain.user.User
import ru.kaplaan.api.web.dto.user.UserDto
import javax.management.relation.RoleNotFoundException


fun User.toDto(): UserDto =
    UserDto(
        email = email,
        username = username,
        password = password
    )


fun UserDto.toEntity(role: Role): User =
    User(
        email = email,
        username = username,
        password = password,
        role = role
    )
