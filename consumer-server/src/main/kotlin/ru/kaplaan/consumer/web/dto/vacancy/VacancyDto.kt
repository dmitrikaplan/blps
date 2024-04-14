package ru.kaplaan.consumer.web.dto.vacancy

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

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

    @field:NotBlank(message = "Название компании не должно быть пустым!")
    val companyName: String,
){
    @field:Null(message = "Id вакансии должен быть равен null!", groups = [OnCreate::class])
    @field:NotNull(message = "Id вакансии не должен быть равен null!", groups = [OnUpdate::class])
    var vacancyId: Long? = null

    var isArchived: Boolean = false
}