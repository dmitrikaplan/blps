package ru.kaplaan.consumer.web.mapper.data

import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.web.dto.data.CompanyDataDto

fun CompanyData.toDto(): CompanyDataDto =
    CompanyDataDto(
        description = this.description,
        site = this.site,
        companyId = this.companyId!!,
        contactPersonDto = this.contactPerson.toDto(),
    )


fun CompanyDataDto.toEntity(): CompanyData =
    CompanyData().apply {
        this.companyId = this@toEntity.companyId
        this.description = this@toEntity.description
        this.site = this@toEntity.site
        this.contactPerson = this@toEntity.contactPersonDto.toEntity()
    }