package ru.kaplaan.api.web.dto.consumerServer.vacancyResponse

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Null
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate

@Schema(description = "Сущность отклика на вакансию")
data class VacancyResponseDto(
    @Schema(description = "Id вакансии", example = "1")
    @field:Min(value = 0, message = "Id вакансии должен быть больше 0", groups = [OnCreate::class, OnUpdate::class])
    val vacancyId: Long
){
    @Schema(description = "Id пользователя", example = "IvanIvanov", hidden = true)
    @field:Null(message = "Id не должен быть заполнен!", groups = [OnCreate::class, OnUpdate::class])
    var userId: Long? = null
}