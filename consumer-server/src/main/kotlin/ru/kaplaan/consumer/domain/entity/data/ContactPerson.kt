package ru.kaplaan.consumer.domain.entity.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(name = "contact_person")
class ContactPerson{
    @Id
    @Column("contact_person_id")
    var id: Long? = null

    lateinit var name: String
    lateinit var surname: String
    lateinit var position: String

    lateinit var email: String

    @Column("company_data_id")
    var companyDataId: Long? = null
}