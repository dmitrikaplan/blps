package ru.kaplaan.consumer.domain.entity.payment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("payment_info")
class PaymentInfo {

    @Id
    @Column("payer_info_id")
    var id: Long? = null

    @Column("company_id")
    var companyId: Long? = null

    lateinit var inn: String
    lateinit var kpp: String
    lateinit var companyAccountNumber: String
    lateinit var companyName: String
    lateinit var bankBik: String
    lateinit var bankAccountNumber: String
    lateinit var bankName: String
}