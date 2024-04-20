package ru.kaplaan.consumer.web.mapper.data

import ru.kaplaan.consumer.domain.entity.data.ContactPerson
import ru.kaplaan.consumer.web.dto.data.ContactPersonDto


fun ContactPerson.toDto(): ContactPersonDto =
    ContactPersonDto(
        name = this.name,
        surname = this.surname,
        position = this.position
    ).apply {
        this.id = this@toDto.id
    }


fun ContactPersonDto.toEntity(): ContactPerson =
    ContactPerson().apply {
        this.name = this@toEntity.name
        this.surname = this@toEntity.surname
        this.position = this@toEntity.position
        this.id = this@toEntity.id
    }