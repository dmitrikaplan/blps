package ru.kaplaan.authserver.web.dto.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length
import ru.kaplaan.authserver.domain.entity.user.Role
import ru.kaplaan.authserver.web.validation.OnCreate

data class UserDto(

    @field:Email(message = "Email should fit the email pattern")
    var email: String,

    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{6,320}$",
        message = "Login should fit the username pattern",
    )
    var username: String,

    @field:Length(
        min = 8, max = 1024,
        message = "The password must be greater than 9, but less than 1025",
    )
    var password: String,


    var role: Role
)