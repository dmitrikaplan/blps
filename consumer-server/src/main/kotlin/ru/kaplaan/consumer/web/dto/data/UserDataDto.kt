package ru.kaplaan.consumer.web.dto.data

import jakarta.validation.constraints.*
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate
import java.time.LocalDate

data class UserDataDto(
    @field:Min(0, message = "Id пользователя не должен быть меньше 0!", groups = [OnCreate::class, OnUpdate::class])
    var userId: Long,

    @field:NotBlank(message = "Имя не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val firstname: String,

    @field:NotBlank(message = "Фамилия не должна быть пуста!", groups = [OnCreate::class, OnUpdate::class])
    val surname: String,

    @field:Past(message = "Дата должна быть в прошлом!", groups = [OnCreate::class, OnUpdate::class])
    val dateOfBirth: LocalDate,

    @field:Pattern(
        regexp = "[+]?\\d{11}",
        message = "Номер телефона должен состоять или из 11 цифр или из 12 с первым знаком '+'",
        groups = [OnCreate::class, OnUpdate::class]
    )
    val phoneNumber: String,

    @field:Email(message = "Почта пользователя должна подходить под паттерн почты!", groups = [OnCreate::class, OnUpdate::class])
    val email: String,

    @field:NotBlank(message = "Должность не должна быть пустой!", groups = [OnCreate::class, OnUpdate::class])
    val position: String,

    val salary: UInt,
    val readyToMove: Boolean,
    val readyForBusinessTrips: Boolean
)