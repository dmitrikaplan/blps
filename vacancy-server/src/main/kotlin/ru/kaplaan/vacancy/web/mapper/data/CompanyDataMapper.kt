package ru.kaplaan.vacancy.web.mapper.data

import ru.kaplaan.vacancy.domain.entity.data.CompanyData
import ru.kaplaan.vacancy.web.dto.data.CompanyDataDto

fun CompanyData.toDto(): CompanyDataDto =
    CompanyDataDto(
        companyName = this.companyName,
        description = this.description,
        site = this.site,
        contactPerson = this.contactPerson.toDto(),
    ).apply {
        this.id = this@toDto.id
    }


fun CompanyDataDto.toEntity(): CompanyData =

    CompanyData().apply {
        companyName = this@toEntity.companyName
        description = this@toEntity.description
        site = this@toEntity.site
        contactPerson = this@toEntity.contactPerson.toEntity()
        id = this@toEntity.id
    }