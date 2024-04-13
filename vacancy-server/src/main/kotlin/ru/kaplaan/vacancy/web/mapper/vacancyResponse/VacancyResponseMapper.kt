package ru.kaplaan.vacancy.web.mapper.vacancyResponse

import ru.kaplaan.vacancy.domain.entity.VacancyResponse
import ru.kaplaan.vacancy.web.dto.vacancyResponse.VacancyResponseDto

fun VacancyResponseDto.toEntity(): VacancyResponse =
    VacancyResponse(
        vacancyId,
        username
    )


fun VacancyResponse.toDto(): VacancyResponseDto =
    VacancyResponseDto(
        username = username,
        vacancyId = pk.vacancyId
    )