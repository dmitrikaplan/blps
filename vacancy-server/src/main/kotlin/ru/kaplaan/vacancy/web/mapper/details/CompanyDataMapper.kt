package ru.kaplaan.vacancy.web.mapper.details

import ru.kaplaan.vacancy.domain.entity.CompanyData
import ru.kaplaan.vacancy.web.dto.details.CompanyDataDto

fun CompanyData.toDto(): CompanyDataDto =
    CompanyDataDto(
        username = this.username,
        companyName = this.companyName,
        description = this.description,
        site = this.site,
        contactPerson = this.contactPerson.toDto(),
    ).apply {
        this.id = this@toDto.id
    }


fun CompanyDataDto.toEntity(): CompanyData =

    CompanyData().apply {
        username = this@toEntity.username
        companyName = this@toEntity.companyName
        description = this@toEntity.description
        site = this@toEntity.site
        contactPerson = this@toEntity.contactPerson.toEntity()
        id = this@toEntity.id
    }