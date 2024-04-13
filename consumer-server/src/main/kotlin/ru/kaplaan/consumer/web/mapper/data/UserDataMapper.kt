package ru.kaplaan.consumer.web.mapper.data

import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.web.dto.data.UserDataDto


fun UserData.toDto(): UserDataDto =
    UserDataDto(
        username = this.username,
        firstname = this.firstname,
        surname = this.surname,
        dateOfBirth = this.dateOfBirth,
        phoneNumber = this.phoneNumber,
        email = this.email,
        position = this.position,
        salary = this.salary,
        readyToMove = this.readyToMove,
        readyForBusinessTrips = this.readyForBusinessTrips
    ).apply {
        this.id = this@toDto.id
    }


fun UserDataDto.toEntity(): UserData =
    UserData().apply {
        id = this@toEntity.id
        username = this@toEntity.username
        firstname = this@toEntity.firstname
        surname = this@toEntity.surname
        dateOfBirth = this@toEntity.dateOfBirth
        phoneNumber = this@toEntity.phoneNumber
        email = this@toEntity.email
        position = this@toEntity.position
        salary = this@toEntity.salary
        readyToMove = this@toEntity.readyToMove
        readyForBusinessTrips = this@toEntity.readyForBusinessTrips
    }