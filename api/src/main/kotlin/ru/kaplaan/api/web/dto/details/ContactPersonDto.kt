package ru.kaplaan.api.web.dto.details

import jakarta.validation.constraints.NotBlank

data class ContactPersonDto(
    @field:NotBlank(message = "Имя не должно быть пустым!")
    val name: String,

    @field:NotBlank(message = "Фамилия не должна быть пустой!")
    val surname: String,

    @field:NotBlank(message = "Должность не должна быть пустой!")
    val position: String
)