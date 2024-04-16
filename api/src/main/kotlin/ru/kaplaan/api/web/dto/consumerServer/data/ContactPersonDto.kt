package ru.kaplaan.api.web.dto.consumerServer.data

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "Сущность контактного лица компании")
data class ContactPersonDto(
    @Schema(description = "Имя контактного лица", example = "Иван")
    @field:NotBlank(message = "Имя не должно быть пустым!")
    val name: String,

    @Schema(description = "Фамилия контактного лица", example = "Иванов")
    @field:NotBlank(message = "Фамилия не должна быть пустой!")
    val surname: String,

    @Schema(description = "Позиция в компании контактного лица", example = "CEO")
    @field:NotBlank(message = "Должность не должна быть пустой!")
    val position: String
)