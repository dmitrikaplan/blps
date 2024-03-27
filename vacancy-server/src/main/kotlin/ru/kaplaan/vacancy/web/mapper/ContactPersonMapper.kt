package ru.kaplaan.vacancy.web.mapper

import ru.kaplaan.vacancy.domain.entity.ContactPerson
import ru.kaplaan.vacancy.web.dto.ContactPersonDto


fun ContactPerson.toDto(): ContactPersonDto =
    ContactPersonDto(
        name = this.name,
        surname = this.surname,
        position = this.position
    )


fun ContactPersonDto.toEntity(): ContactPerson =
    ContactPerson().apply {
        this.name = this@toEntity.name
        this.surname = this@toEntity.surname
        this.position = this@toEntity.position
    }