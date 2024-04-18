package ru.kaplaan.api.web.dto.consumerServer.data

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import org.hibernate.validator.constraints.URL
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate

@Schema(description = "Сущность информации о компании")
data class CompanyDataDto(

    @Schema(description = "Описание компании", example = "Компания занимается разработкой ПО")
    @field:NotBlank(message = "Описание не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val description: String,

    @Schema(description = "URL компании", example = "company.ru")
    @field:URL(message = "URL сайта должен подходить под паттерн URL", groups = [OnCreate::class, OnUpdate::class])
    val site: String,

    @Schema(description = "Контактное лицо")
    @field:NotNull(message = "Данные контактного лица должны быть заполнены!", groups = [OnCreate::class, OnUpdate::class])
    val contactPerson: ContactPersonDto
){
    @Schema(description = "Название компании", hidden = true)
    @field:Null(message = "Название компании не должно быть заполнено!!", groups = [OnCreate::class, OnUpdate::class])
    var companyName: String? = null
}