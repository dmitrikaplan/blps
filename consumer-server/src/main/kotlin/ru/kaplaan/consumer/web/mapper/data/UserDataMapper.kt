package ru.kaplaan.consumer.web.mapper.data

import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.web.dto.data.UserDataDto


fun UserData.toDto(): UserDataDto =
    UserDataDto(
        firstname = this.firstname,
        surname = this.surname,
        dateOfBirth = this.dateOfBirth,
        phoneNumber = this.phoneNumber,
        email = this.email,
        position = this.position,
        salary = this.salary,
        readyToMove = this.readyToMove,
        userId = this.userId!!,
        readyForBusinessTrips = this.readyForBusinessTrips
    )


fun UserDataDto.toEntity(): UserData =
    UserData().apply {
        this.userId = this@toEntity.userId
        this.firstname = this@toEntity.firstname
        this.surname = this@toEntity.surname
        this.dateOfBirth = this@toEntity.dateOfBirth
        this.phoneNumber = this@toEntity.phoneNumber
        this.email = this@toEntity.email
        this.position = this@toEntity.position
        this.salary = this@toEntity.salary
        this.readyToMove = this@toEntity.readyToMove
        this.readyForBusinessTrips = this@toEntity.readyForBusinessTrips
    }