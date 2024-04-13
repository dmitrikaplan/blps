package ru.kaplaan.consumer.web.mapper.data

import ru.kaplaan.consumer.domain.entity.data.ContactPerson
import ru.kaplaan.consumer.web.dto.data.ContactPersonDto


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