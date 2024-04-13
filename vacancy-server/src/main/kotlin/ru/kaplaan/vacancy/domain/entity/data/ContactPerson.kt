package ru.kaplaan.vacancy.domain.entity.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

class ContactPerson{

    @Id
    @Column("contact_person_id")
    var id: Long? = null

    lateinit var name: String
    lateinit var surname: String
    lateinit var position: String
}