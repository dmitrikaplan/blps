package ru.kaplaan.consumer.web.dto.vacancy

import jakarta.validation.constraints.Min
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

data class VacancyResponseDto(
    @field:Min(0, message = "Id пользователя не должен быть меньше 0!", groups = [OnCreate::class, OnUpdate::class])
    val userId: Long,
    @field:Min(value = 0, message = "Id вакансии должен быть больше 0", groups = [OnCreate::class, OnUpdate::class])
    val vacancyId: Long
)