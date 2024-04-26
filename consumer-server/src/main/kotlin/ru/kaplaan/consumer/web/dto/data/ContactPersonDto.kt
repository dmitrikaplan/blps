package ru.kaplaan.consumer.web.dto.data

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

data class ContactPersonDto(
    @field:NotBlank(message = "Имя контактного лица не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val name: String,

    @field:NotBlank(message = "Фамилия контактного лица не должна быть пустой!", groups = [OnCreate::class, OnUpdate::class])
    val surname: String,

    @field:NotBlank(message = "Должность контактного лица не должна быть пустой!", groups = [OnCreate::class, OnUpdate::class])
    val position: String,

    @field:Email(message = "Электронная почта должна подходить под паттерн email", groups = [OnCreate::class, OnUpdate::class])
    val email: String
)