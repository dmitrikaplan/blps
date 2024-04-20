package ru.kaplaan.consumer.domain.entity.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("company_data")
class CompanyData{

    @Id
    @Column("company_data_id")
    var id: Long? = null

    @Column("company_id")
    var companyId: Long? = null
    lateinit var description: String
    lateinit var site: String

    @MappedCollection(idColumn = "company_data_id")
    lateinit var contactPerson: ContactPerson
}

