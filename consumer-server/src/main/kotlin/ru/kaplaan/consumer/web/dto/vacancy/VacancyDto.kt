package ru.kaplaan.consumer.web.dto.vacancy

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

data class VacancyDto(

    @field:NotBlank(message = "Заголовок вакансии не должен быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val title: String,

    val salaryRange: IntRange?,

    val currency: Currency,

    @field:NotBlank(message = "Адрес не должен быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val address: String,

    @field:NotBlank(message = "Описание не должно быть пустым!", groups = [OnCreate::class, OnUpdate::class])
    val description: String,

    val hashTags: List<String>,

    @field:Min(0, message = "Id компании не должно быть меньше 0!", groups = [OnCreate::class, OnUpdate::class])
    val companyId: Long,
    var isArchived: Boolean
){
    @field:Null(message = "Id вакансии должен быть равен null!", groups = [OnCreate::class])
    @field:NotNull(message = "Id вакансии не должен быть равен null!", groups = [OnUpdate::class])
    var id: Long? = null
}