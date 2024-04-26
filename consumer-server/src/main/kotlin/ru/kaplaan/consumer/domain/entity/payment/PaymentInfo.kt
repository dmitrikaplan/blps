package ru.kaplaan.consumer.domain.entity.payment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("payment_info")
class PaymentInfo {

    @Id
    @Column("payment_info_id")
    var id: Long? = null

    @Column("company_id")
    var companyId: Long? = null


    @Column("inn")
    lateinit var inn: String
    @Column("kpp")
    lateinit var kpp: String
    @Column("company_account_number")
    lateinit var companyAccountNumber: String
    @Column("company_name")
    lateinit var companyName: String
    @Column("bank_bik")
    lateinit var bankBik: String
    @Column("bank_account_number")
    lateinit var bankAccountNumber: String
    @Column("bank_name")
    lateinit var bankName: String
}