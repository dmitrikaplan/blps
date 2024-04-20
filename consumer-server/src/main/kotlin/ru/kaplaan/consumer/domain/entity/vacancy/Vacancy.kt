package ru.kaplaan.consumer.domain.entity.vacancy

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import ru.kaplaan.consumer.web.dto.vacancy.Currency

@Table("vacancy")
class Vacancy{
    @Id
    @Column("vacancy_id")
    var id: Long? = null

    @Column("company_id")
    var companyId: Long? = null

    lateinit var title: String
    var salary: String? = null
    lateinit var address: String
    lateinit var description: String
    lateinit var hashTags: String
    lateinit var currency: Currency

    @Column("is_archived")
    var isArchived: Boolean = false
}