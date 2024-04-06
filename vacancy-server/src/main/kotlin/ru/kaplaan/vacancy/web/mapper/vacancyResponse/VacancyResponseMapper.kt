package ru.kaplaan.vacancy.web.mapper.vacancyResponse

import ru.kaplaan.vacancy.domain.entity.VacancyResponse
import ru.kaplaan.vacancy.web.dto.vacancyResponse.VacancyResponseDto

fun VacancyResponseDto.toEntity(): VacancyResponse =
    VacancyResponse(
        userId = userId,
        vacancyId = vacancyId
    )


fun VacancyResponse.toDto(): VacancyResponseDto =
    VacancyResponseDto(
        userId = userId,
        vacancyId = vacancyId
    ).apply {
         vacancyResponseId = this@toDto.id
    }