package ru.kaplaan.api.web.dto.consumerServer.details

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Null
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate
import java.time.LocalDate

data class UserDataDto(

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
){
    @field:Null(message = "Username не должен быть заполнен!", groups = [OnCreate::class])
    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{6,320}$",
        message = "Login should fit the username pattern",
        groups = [OnUpdate::class]
    )
    var username: String? = null
}