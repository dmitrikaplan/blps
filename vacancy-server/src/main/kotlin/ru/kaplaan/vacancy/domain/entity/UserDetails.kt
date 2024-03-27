package ru.kaplaan.vacancy.domain.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("user_details")
class UserDetails{

    @Id
    @Column("user_details_id")
    var id: Long? = null

    lateinit var username: String
    lateinit var firstname: String
    lateinit var surname: String
    lateinit var dateOfBirth: LocalDate
    lateinit var phoneNumber: String
    lateinit var email: String
    lateinit var position: String

    var salary: UInt = 0u
    var readyToMove: Boolean = false
    var readyForBusinessTrips = false

}