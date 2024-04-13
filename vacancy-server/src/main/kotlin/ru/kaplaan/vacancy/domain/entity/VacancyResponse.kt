package ru.kaplaan.vacancy.domain.entity

import org.springframework.data.annotation.Transient
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("vacancy_response")
data class VacancyResponse(
    @Transient
    val username: String,
    @Column("vacancy_id")
    val vacancyId: Long
) {

    //TODO: добавить составной первичный ключ
    @Column("user_id")
    var userId: Long? = null
}