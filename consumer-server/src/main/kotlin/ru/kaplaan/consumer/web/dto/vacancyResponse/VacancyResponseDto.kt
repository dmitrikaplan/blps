package ru.kaplaan.consumer.web.dto.vacancyResponse

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Null
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

data class VacancyResponseDto(
    @field:NotBlank(message = "Username не должен быть пустым!")
    val username: String,
    @field:Min(value = 0, message = "Id вакансии должен быть больше 0", groups = [OnCreate::class, OnUpdate::class])
    val vacancyId: Long
){
    @field:Null(message = "Id отклика на вакансию должен быть null!")
    var vacancyResponseId: Long? = null
}