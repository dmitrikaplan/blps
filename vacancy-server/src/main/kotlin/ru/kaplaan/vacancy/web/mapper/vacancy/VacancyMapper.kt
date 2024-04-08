package ru.kaplaan.vacancy.web.mapper.vacancy

import ru.kaplaan.vacancy.domain.entity.vacancy.Vacancy
import ru.kaplaan.vacancy.web.dto.vacancy.VacancyDto

fun Vacancy.toDto(): VacancyDto =
    VacancyDto(
        title = title,
        salaryRange = salary.toRange(),
        currency = currency,
        address = address,
        description = description,
        hashTags = hashTags.hashTagsToList(),
        companyName = companyName
    )


fun VacancyDto.toEntity(): Vacancy =
    Vacancy().apply {
        title = this@toEntity.title
        salary = this@toEntity.salaryRange.toSalary()
        address = this@toEntity.address
        description = this@toEntity.description
        hashTags = this@toEntity.hashTags.hashTagsToString()
        currency = this@toEntity.currency
        companyName = this@toEntity.companyName
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


