package ru.kaplaan.consumer.web.dto.email

import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseStatus

data class VacancyResponseEmailDto(
    val email: String,
    val firstname: String,
    val surname: String,
    val vacancyTitle: String,
    val comment: String,
    val status: VacancyResponseStatus
)