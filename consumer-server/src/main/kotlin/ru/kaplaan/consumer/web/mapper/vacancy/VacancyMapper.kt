package ru.kaplaan.consumer.web.mapper.vacancy

import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.web.dto.vacancy.VacancyDto

fun Vacancy.toDto(): VacancyDto =
    VacancyDto(
        title = title,
        salaryRange = salary.toRange(),
        currency = currency,
        address = address,
        description = description,
        hashTags = hashTags.hashTagsToList(),
        companyId = companyId!!,
        isArchived = isArchived
    ).apply {
        this.id = this@toDto.id
    }


fun VacancyDto.toEntity(): Vacancy =
    Vacancy().apply {
        this.companyId = this@toEntity.companyId
        this.title = this@toEntity.title
        this.salary = salaryRange.toSalary()
        this.address = this@toEntity.address
        this.description = this@toEntity.description
        this.hashTags = this@toEntity.hashTags.hashTagsToString()
        this.currency = this@toEntity.currency
        this.isArchived = this@toEntity.isArchived
        this.id = this@toEntity.id
    }


fun List<Vacancy>.toDto(): List<VacancyDto> =
    this.map { it.toDto() }

private fun IntRange?.toSalary(): String? =
    when{

        this == null -> null

        this.first == this.last -> "${this.first}"

        else -> "${this.first}-${this.last}"
    }


private fun String?.toRange(): IntRange? =

    when{
        this == null -> null

        else -> split("-")
            .map { it.toInt() }
            .let {
                it.first()..it.last()
            }
    }

private fun List<String>.hashTagsToString(): String =
    this.joinToString("/")


private fun String.hashTagsToList(): List<String> =
    this.split("/")


