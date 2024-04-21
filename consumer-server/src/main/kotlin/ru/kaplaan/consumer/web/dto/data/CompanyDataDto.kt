package ru.kaplaan.consumer.web.dto.data

import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.URL
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

data class CompanyDataDto(
    @field:Min(0, message = "Id компании должен быть не меньше 0!", groups = [OnCreate::class, OnUpdate::class])
    val companyId: Long,

    @field:NotBlank(message = "Описание не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val description: String,

    @field:URL(message = "URL сайта должен подходить под паттерн URL", groups = [OnCreate::class, OnUpdate::class])
    val site: String,

    @field:Valid
    val contactPersonDto: ContactPersonDto
)