package ru.kaplaan.authserver.web.dto.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length
import ru.kaplaan.authserver.domain.entity.user.Role
import ru.kaplaan.authserver.web.validation.OnCreate
import ru.kaplaan.authserver.web.validation.OnUpdate

data class UserDto(

    @field:Email(message = "Email should fit the email pattern")
    var email: String,

    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{6,320}$",
        message = "Username должен содержать только символы [a-zA-Z0-9] и иметь длину из промежутка {6,320}",
        groups = [OnCreate::class, OnUpdate::class]
    )
    var username: String,

    @field:Length(
        min = 8, max = 1024,
        message = "Длина пароля должна быть не меньше 8 и не более 1024 символов",
        groups = [OnCreate::class, OnUpdate::class]
    )
    var password: String,


    var role: Role
)