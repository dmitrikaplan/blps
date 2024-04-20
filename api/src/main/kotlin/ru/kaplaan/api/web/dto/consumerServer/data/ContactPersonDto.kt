package ru.kaplaan.api.web.dto.consumerServer.data

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate

@Schema(description = "Сущность контактного лица компании")
data class ContactPersonDto(
    @Schema(description = "Имя контактного лица", example = "Иван")
    @field:NotBlank(message = "Имя не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val name: String,

    @Schema(description = "Фамилия контактного лица", example = "Иванов")
    @field:NotBlank(message = "Фамилия не должна быть пустой!", groups = [OnCreate::class, OnUpdate::class])
    val surname: String,

    @Schema(description = "Позиция в компании контактного лица", example = "CEO")
    @field:NotBlank(message = "Должность не должна быть пустой!", groups = [OnCreate::class, OnUpdate::class])
    val position: String
){
    @field:Null(message = "Id контактного лица не должно быть заполнено!", groups = [OnCreate::class])
    @field:NotNull(message = "Id контактного лица должно быть заполнено!", groups = [OnUpdate::class])
    var id: Long? = null
}