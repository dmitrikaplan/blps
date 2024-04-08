package ru.kaplaan.api.web.dto.consumerServer.details

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Null
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.URL
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate

data class CompanyDataDto(

    @field:NotBlank(message = "Название компании не должно быть пустым!")
    val companyName: String,

    @field:NotBlank(message = "Описание не должно быть пустым!")
    val description: String,

    @field:URL(message = "URL сайта должен подходить под паттерн URL")
    val site: String,

    val contactPerson: ContactPersonDto
){
    @field:Null(message = "Username не должен быть заполнен!", groups = [OnCreate::class])
    @field:Pattern(
        regexp = "^[a-zA-Z0-9]{6,320}$",
        message = "Login should fit the username pattern",
        groups = [OnUpdate::class]
    )
    var username: String? = null
}