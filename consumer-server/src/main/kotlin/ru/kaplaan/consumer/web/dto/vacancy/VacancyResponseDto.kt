package ru.kaplaan.consumer.web.dto.vacancy

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Null
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate
import java.time.LocalDate

data class VacancyResponseDto(
    @field:Min(0, message = "Id пользователя не должен быть меньше 0!", groups = [OnCreate::class, OnUpdate::class])
    val userId: Long,
    @field:Min(value = 0, message = "Id вакансии должен быть больше 0", groups = [OnCreate::class, OnUpdate::class])
    val vacancyId: Long
){
    @field:Null(message = "Комментарий не должен быть заполнен!", groups = [OnCreate::class])
    @field:NotNull(message = "Комментарий должен быть заполнен!", groups = [OnUpdate::class])
    var comment: String? = null

    @field:Null(message = "Статус вакансии не должен быть заполнен!", groups = [OnCreate::class])
    @field:NotNull(message = "Статус вакансии должен быть заполнен!", groups = [OnUpdate::class])
    var status: VacancyResponseStatus? = null

    @field:Null(message = "Дата обновления статуса вакансии не должен быть заполнен!", groups = [OnCreate::class, OnUpdate::class])
    var dateLastStatusUpdate: LocalDate? = null
}