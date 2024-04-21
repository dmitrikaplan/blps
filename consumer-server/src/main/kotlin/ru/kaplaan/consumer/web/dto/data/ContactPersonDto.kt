package ru.kaplaan.consumer.web.dto.data

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Null
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

data class ContactPersonDto(
    @field:NotBlank(message = "Имя контактного лица не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val name: String,

    @field:NotBlank(message = "Фамилия контактного лица не должна быть пустой!", groups = [OnCreate::class, OnUpdate::class])
    val surname: String,

    @field:NotBlank(message = "Должность контактного лица не должна быть пустой!", groups = [OnCreate::class, OnUpdate::class])
    val position: String
){

    @field:Null(message = "Id контактного лица не должно быть заполнено!", groups = [OnCreate::class, OnUpdate::class])
    var id: Long? = null
}