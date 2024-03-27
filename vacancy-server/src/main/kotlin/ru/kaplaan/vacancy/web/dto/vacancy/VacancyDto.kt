package ru.kaplaan.vacancy.web.dto.vacancy

import jakarta.validation.constraints.NotBlank

data class VacancyDto(

    @field:NotBlank(message = "Заголовок вакансии не должен быть пустым!")
    val title: String,

    val salaryRange: IntRange,

    val currency: Currency,

    @field:NotBlank(message = "Адрес не должен быть пустым!")
    val address: String,

    @field:NotBlank(message = "Описание не должно быть пустым!")
    val description: String,

    val hashTags: List<String>,

    val companyId: Long,
)