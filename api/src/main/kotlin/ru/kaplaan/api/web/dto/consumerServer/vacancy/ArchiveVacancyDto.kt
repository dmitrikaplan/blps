package ru.kaplaan.api.web.dto.consumerServer.vacancy

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Null
import ru.kaplaan.api.web.validation.OnCreate

@Schema(description = "Сущность (раз)архивирования вакансии")
data class ArchiveVacancyDto(
    @Schema(description = "Id вакансии", example = "1")
    val vacancyId: Long
){
    @Schema(description = "Название компании", example = "Yandex", hidden = true)
    @field:Null(message = "Название компании должно быть пустым!", groups = [OnCreate::class])
    var companyName: String? = null
}