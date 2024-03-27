package ru.kaplaan.vacancy.web.mapper.details

import ru.kaplaan.vacancy.domain.entity.ContactPerson
import ru.kaplaan.vacancy.web.dto.details.ContactPersonDto


fun ContactPerson.toDto(): ContactPersonDto =
    ContactPersonDto(
        name = this.name,
        surname = this.surname,
        position = this.position
    )


fun ContactPersonDto.toEntity(): ContactPerson =
    ContactPerson().apply {
        name = this@toEntity.name
        surname = this@toEntity.surname
        position = this@toEntity.position
    }