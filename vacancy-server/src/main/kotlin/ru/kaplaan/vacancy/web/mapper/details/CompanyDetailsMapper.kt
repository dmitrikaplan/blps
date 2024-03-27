package ru.kaplaan.vacancy.web.mapper.details

import ru.kaplaan.vacancy.domain.entity.CompanyDetails
import ru.kaplaan.vacancy.web.dto.details.CompanyDetailsDto

fun CompanyDetails.toDto(): CompanyDetailsDto =
    CompanyDetailsDto(
        username = this.username,
        companyName = this.companyName,
        description = this.description,
        site = this.site,
        contactPerson = this.contactPerson.toDto(),
    )


fun CompanyDetailsDto.toEntity(): CompanyDetails =

    CompanyDetails().apply {
        username = this@toEntity.username
        companyName = this@toEntity.companyName
        description = this@toEntity.description
        site = this@toEntity.site
        contactPerson = this@toEntity.contactPerson.toEntity()
    }