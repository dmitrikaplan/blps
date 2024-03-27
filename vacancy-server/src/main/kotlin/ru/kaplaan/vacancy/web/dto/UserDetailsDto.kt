package ru.kaplaan.vacancy.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import java.time.LocalDate

data class UserDetailsDto(
    @field:NotBlank(message = "Имя не должно быть пустым!")
    val firstname: String,

    @field:NotBlank(message = "Фамилия не должна быть пуста!")
    val surname: String,

    val dateOfBirth: LocalDate,

    @field:Length(min = 12, max = 12, message = "Номер телефон должен состоять из 12 символов!")
    val phoneNumber: String,

    @field:Email
    val email: String,

    @field:NotBlank(message = "Должность не должна быть пустой!")
    val position: String,

    val salary: UInt,

    val readyToMove: Boolean,

    val readyForBusinessTrips: Boolean
)