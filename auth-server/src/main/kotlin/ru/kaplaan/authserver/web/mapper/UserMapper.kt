package ru.kaplaan.authserver.web.mapper

import ru.kaplaan.authserver.domain.entity.user.User
import ru.kaplaan.authserver.domain.user.UserIdentification
import ru.kaplaan.authserver.web.dto.user.UserDto
import ru.kaplaan.authserver.web.dto.user.UserIdentificationDto


fun User.toDto(): UserDto {
    return UserDto(
        email = email,
        username = username,
        password = password,
        role = role
    )
}


fun UserDto.toEntity(): User =
        User(
            email = email,
            username = username,
            password = password,
            role = role
        )


fun UserIdentification.toDto(): UserIdentificationDto {
    return UserIdentificationDto(
        usernameOrEmail = usernameOrEmail,
        password = password
    )
}


fun UserIdentificationDto.toEntity(): UserIdentification {
    return UserIdentification(
        usernameOrEmail = usernameOrEmail,
        password = password
    )
}