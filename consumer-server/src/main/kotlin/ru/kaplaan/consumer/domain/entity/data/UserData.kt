package ru.kaplaan.consumer.domain.entity.data

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("user_data")
class UserData{

    @Id
    @Column("user_data_id")
    var id: Long? = null

    @Column("user_id")
    var userId: Long? = null
    lateinit var firstname: String
    lateinit var surname: String
    lateinit var dateOfBirth: LocalDate
    lateinit var phoneNumber: String
    lateinit var email: String
    lateinit var position: String
    var salary: UInt = 0u
    var readyToMove: Boolean = false
    var readyForBusinessTrips: Boolean = false
}