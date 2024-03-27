package ru.kaplaan.vacancy.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import ru.kaplaan.vacancy.web.dto.vacancy.Currency

@Table("vacancy")
class Vacancy() {

    @Id
    var id: Long? = null

    lateinit var title: String
    lateinit var salary: String
    lateinit var address: String
    lateinit var description: String
    lateinit var hashTags: String
    lateinit var currency: Currency

    @Column("company_id")
    var companyId: Long? = null


    constructor(
        title: String,
        salary: String,
        address: String,
        description: String,
        hashTags: String
    ): this(){
        this.title = title
        this.salary = salary
        this.address = address
        this.description = description
        this.hashTags = hashTags
    }
}