package ru.kaplaan.consumer.web.dto.vacancy

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import ru.kaplaan.consumer.web.validation.OnCreate

data class ArchiveVacancyDto(
    @field:NotBlank(message = "Название компании не должно быть пустым!",  groups = [OnCreate::class])
    val companyName: String,
    @field:Min(0, message = "Минимальный Id вакансии 0!", groups = [OnCreate::class])
    val vacancyId: Long
)