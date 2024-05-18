package ru.kaplaan.consumer.web.mapper.vacancy

import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseDto
import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseStatus

fun VacancyResponseDto.toEntity(): VacancyResponse =
    VacancyResponse().apply {
        pk = VacancyResponse.PK(vacancyId, userId)
    }.apply {
        this.comment = this@toEntity.comment ?: VacancyResponseStatus.IN_PROCESSING.defaultComment
        this.status = this@toEntity.status ?: VacancyResponseStatus.IN_PROCESSING
    }


fun VacancyResponse.toDto(): VacancyResponseDto =
    VacancyResponseDto(
        userId = pk.userId,
        vacancyId = pk.vacancyId
    ).apply {
        this.comment = this@toDto.comment
        this.status = this@toDto.status
        this.dateLastStatusUpdate = this@toDto.dateLastStatusUpdate
    }



fun List<VacancyResponse>.toDto() =
    this.map { it.toDto() }