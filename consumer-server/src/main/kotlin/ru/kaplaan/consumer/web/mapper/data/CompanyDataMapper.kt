package ru.kaplaan.consumer.web.mapper.data

import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.web.dto.data.CompanyDataDto

fun CompanyData.toDto(): CompanyDataDto =
    CompanyDataDto(
        companyName = this.companyName,
        description = this.description,
        site = this.site,
        contactPerson = this.contactPerson.toDto(),
    )


fun CompanyDataDto.toEntity(): CompanyData =

    CompanyData().apply {
        companyName = this@toEntity.companyName
        description = this@toEntity.description
        site = this@toEntity.site
        contactPerson = this@toEntity.contactPerson.toEntity()
    }