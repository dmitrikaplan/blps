package ru.kaplaan.api.web.dto.consumerServer.vacancyResponse

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Null
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate

data class VacancyResponseDto(
    @field:Min(value = 0, message = "Id вакансии должен быть больше 0", groups = [OnCreate::class, OnUpdate::class])
    val vacancyId: Long
){

    @field:Null(message = "Username не должен быть заполнен!", groups = [OnCreate::class])
    var username: String? = null

    @field:Null(message = "Id отклика на вакансию не должен быть заполнен!", groups = [OnCreate::class])
    var vacancyResponseId: Long? = null
}