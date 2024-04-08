package ru.kaplaan.api.web.dto.consumerServer.vacancy

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Null
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate

data class VacancyDto(

    @field:NotBlank(message = "Заголовок вакансии не должен быть пустым!")
    val title: String,

    val salaryRange: IntRange?,

    val currency: Currency,

    @field:NotBlank(message = "Адрес не должен быть пустым!")
    val address: String,

    @field:NotBlank(message = "Описание не должно быть пустым!")
    val description: String,

    val hashTags: List<String>,
){
    @field:Null(message = "Имя компании не должно быть заполнено!", groups = [OnCreate::class, OnUpdate::class])
    var companyName: String? = null
}