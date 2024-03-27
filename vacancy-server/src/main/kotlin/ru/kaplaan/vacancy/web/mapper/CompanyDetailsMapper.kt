package ru.kaplaan.vacancy.web.mapper

import ru.kaplaan.vacancy.domain.entity.CompanyDetails
import ru.kaplaan.vacancy.web.dto.CompanyDetailsDto

fun CompanyDetails.toDto(): CompanyDetailsDto =
    CompanyDetailsDto(
        companyName = this.companyName,
        description = this.description,
        site = this.site,
        contactPerson = this.contactPerson.toDto()
    )


fun CompanyDetailsDto.toEntity(): CompanyDetails =

    CompanyDetails().apply {
        this.companyName = this@toEntity.companyName
        this.description = this@toEntity.description
        this.site = this@toEntity.site
        this.contactPerson = this@toEntity.contactPerson.toEntity()
    }