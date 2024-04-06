package ru.kaplaan.vacancy.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("vacancy_id")
data class VacancyResponse(
    val userId: Long,
    val vacancyId: Long
) {
    @Id
    @Column("vacancy_response_id")
    val id: Long? = null
}