package ru.kaplaan.vacancy.web.mapper

import ru.kaplaan.vacancy.domain.entity.UserDetails
import ru.kaplaan.vacancy.web.dto.UserDetailsDto


fun UserDetails.toDto(): UserDetailsDto =
    UserDetailsDto(
        firstname = this.firstname,
        surname = this.surname,
        dateOfBirth = this.dateOfBirth,
        phoneNumber = this.phoneNumber,
        email = this.email,
        position = this.position,
        salary = this.salary,
        readyToMove = this.readyToMove,
        readyForBusinessTrips = this.readyForBusinessTrips
    )


fun UserDetailsDto.toEntity(): UserDetails =
    UserDetails().apply {
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