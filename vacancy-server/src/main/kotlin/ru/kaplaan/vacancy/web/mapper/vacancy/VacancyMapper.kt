package ru.kaplaan.vacancy.web.mapper.vacancy

import ru.kaplaan.vacancy.domain.entity.Vacancy
import ru.kaplaan.vacancy.web.dto.vacancy.VacancyDto

fun Vacancy.toDto(): VacancyDto =
    VacancyDto(
        title = title,
        salaryRange = salary.toRange(),
        currency = currency,
        address = address,
        description = description,
        hashTags = hashTags.hashTagsToList(),
        companyId = companyId!!
    )


fun VacancyDto.toEntity(companyId: Long): Vacancy =
    Vacancy().apply {
        title = this@toEntity.title
        salary = this@toEntity.salaryRange.toSalary()
        address = this@toEntity.address
        description = this@toEntity.description
        hashTags = this@toEntity.hashTags.hashTagsToString()
        currency = this@toEntity.currency
        this.companyId = companyId
    }



private fun IntRange.toSalary(): String{
   return if(this.first == this.last)
            "${this.first}"
        else "${this.first}-${this.last}"
}


private fun String.toRange(): IntRange =
    split("-")
        .map { it.toInt() }
        .let {
            it.first()..it.last()
        }


private fun List<String>.hashTagsToString(): String =
    this.joinToString("/")


private fun String.hashTagsToList(): List<String> =
    this.split("/")


