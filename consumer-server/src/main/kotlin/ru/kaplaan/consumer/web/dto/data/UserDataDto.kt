package ru.kaplaan.consumer.web.dto.data

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import org.hibernate.validator.constraints.Length
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate
import java.time.LocalDate

data class UserDataDto(
    @field:NotBlank(message = "Username пользователя не должен быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val username: String,

    @field:NotBlank(message = "Имя не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val firstname: String,

    @field:NotBlank(message = "Фамилия не должна быть пуста!", groups = [OnCreate::class, OnUpdate::class])
    val surname: String,

    val dateOfBirth: LocalDate,

    @field:Length(min = 12, max = 12, message = "Номер телефон должен состоять из 12 символов!", groups = [OnCreate::class, OnUpdate::class])
    val phoneNumber: String,

    @field:Email(message = "Почта пользователя должна подходить под паттерн почты!", groups = [OnCreate::class, OnUpdate::class])
    val email: String,

    @field:NotBlank(message = "Должность не должна быть пустой!", groups = [OnCreate::class, OnUpdate::class])
    val position: String,

    val salary: UInt,
    val readyToMove: Boolean,
    val readyForBusinessTrips: Boolean
){
    @field:NotNull(message = "Id данных пользователя должен быть заполнен!", groups = [OnUpdate::class])
    @field:Null(message = "Id данных пользователя не должен быть заполнен!", groups = [OnCreate::class])
    var id: Long? = null
}