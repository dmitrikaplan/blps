package ru.kaplaan.vacancy.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("company_data")
class CompanyData{

    @Id
    @Column("company_data_id")
    var id: Long? = null

    lateinit var username: String
    lateinit var companyName: String
    lateinit var description: String
    lateinit var site: String

    @MappedCollection(idColumn = "company_person_id")
    lateinit var contactPerson: ContactPerson

}

