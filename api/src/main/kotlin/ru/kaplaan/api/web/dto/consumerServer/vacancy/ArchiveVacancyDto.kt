package ru.kaplaan.api.web.dto.consumerServer.vacancy

import jakarta.validation.constraints.Null
import ru.kaplaan.api.web.validation.OnCreate

data class ArchiveVacancyDto(
    val vacancyId: Long
){
    @field:Null(message = "Название компании должно быть пустым!", groups = [OnCreate::class])
    var companyName: String? = null
}