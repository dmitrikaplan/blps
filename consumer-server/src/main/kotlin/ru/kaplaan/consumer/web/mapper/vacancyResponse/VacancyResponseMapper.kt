package ru.kaplaan.consumer.web.mapper.vacancyResponse

import ru.kaplaan.consumer.domain.entity.vacancyResponse.VacancyResponse
import ru.kaplaan.consumer.web.dto.vacancyResponse.VacancyResponseDto

fun VacancyResponseDto.toEntity(): VacancyResponse =
    VacancyResponse().apply {
        pk = VacancyResponse.PK(vacancyId, userId)
    }


fun VacancyResponse.toDto(): VacancyResponseDto =
    VacancyResponseDto(
        userId = pk.userId,
        vacancyId = pk.vacancyId
    )