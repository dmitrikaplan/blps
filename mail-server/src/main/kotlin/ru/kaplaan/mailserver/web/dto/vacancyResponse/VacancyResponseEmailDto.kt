package ru.kaplaan.mailserver.web.dto.vacancyResponse

import kotlinx.serialization.Serializable

@Serializable
data class VacancyResponseEmailDto(
    val email: String,
    val firstname: String,
    val surname: String,
    val vacancyTitle: String,
    val comment: String,
    val status: String
)