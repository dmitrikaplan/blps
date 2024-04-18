package ru.kaplaan.consumer.web.dto.data

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import org.hibernate.validator.constraints.URL
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

data class CompanyDataDto(
    @field:NotBlank(message = "Название компании не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val companyName: String,

    @field:NotBlank(message = "Описание не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val description: String,

    @field:URL(message = "URL сайта должен подходить под паттерн URL", groups = [OnCreate::class, OnUpdate::class])
    val site: String,

    val contactPerson: ContactPersonDto
){
    @field:NotNull(message = "Id данных компании должен быть заполнен!", groups = [OnUpdate::class])
    @field:Null(message = "Id данных компании не должен быть заполнен!", groups = [OnCreate::class])
    var id: Long? = null
}