package ru.kaplaan.consumer.web.dto.vacancy

import jakarta.validation.constraints.Min
import ru.kaplaan.consumer.web.validation.OnCreate

data class ArchiveVacancyDto(
    @field:Min(0, message = "Минимальный Id компании 0!", groups = [OnCreate::class])
    val companyId: Long,
    @field:Min(0, message = "Минимальный Id вакансии 0!", groups = [OnCreate::class])
    val vacancyId: Long
)