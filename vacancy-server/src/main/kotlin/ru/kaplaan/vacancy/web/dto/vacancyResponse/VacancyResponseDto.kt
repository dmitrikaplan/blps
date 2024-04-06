package ru.kaplaan.vacancy.web.dto.vacancyResponse

import jakarta.validation.constraints.Min

data class VacancyResponseDto(
    @Min(value = 0, message = "Id пользователя должен быть больше 0")
    val userId: Long,
    @Min(value = 0, message = "Id вакансии должен быть больше 0")
    val vacancyId: Long
){

    var vacancyResponseId: Long? = null
}