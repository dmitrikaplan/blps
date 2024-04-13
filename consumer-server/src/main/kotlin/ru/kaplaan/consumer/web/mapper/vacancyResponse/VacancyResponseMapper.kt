package ru.kaplaan.consumer.web.mapper.vacancyResponse

import ru.kaplaan.consumer.domain.entity.VacancyResponse
import ru.kaplaan.consumer.web.dto.vacancyResponse.VacancyResponseDto

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