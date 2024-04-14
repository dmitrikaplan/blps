package ru.kaplaan.consumer.web.dto.vacancy

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class ArchiveVacancyDto(
    @field:NotBlank(message = "Название компании не должно быть пустым!")
    val companyName: String,
    @field:Min(0, message = "Минимальный Id вакансии 0!")
    val vacancyId: Long
)