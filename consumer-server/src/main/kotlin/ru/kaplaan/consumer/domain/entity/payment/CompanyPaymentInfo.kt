package ru.kaplaan.consumer.domain.entity.payment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("company_payment_info")
class CompanyPaymentInfo {
    @Id
    @Column("company_payment_info_id")
    var id: Long? = null

    lateinit var inn: String
    lateinit var kpp: String
    lateinit var companyName: String
    lateinit var companyAccountNumber: String
    lateinit var bankBik: String
    lateinit var bankAccountNumber: String
    lateinit var bankName: String
    @Column("purpose_of_payment")
    lateinit var purposeOfPayment: String

    var sum: Long? = null
}