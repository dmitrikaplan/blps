package ru.kaplaan.consumer.domain.entity.payment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("payment_order")
class PaymentOrder {

    @Id
    @Column("payment_order_id")
    var id: Long? = null

    lateinit var payerInn: String
    lateinit var payerKpp: String
    lateinit var payerCompanyName: String
    lateinit var payerCompanyAccountNumber: String
    lateinit var payerBankBik: String
    lateinit var payerBankAccountNumber: String
    lateinit var payerBankName: String
    var payerCompanyId: Long? = null

    lateinit var recipientInn: String
    lateinit var recipientKpp: String
    lateinit var recipientCompanyName: String
    lateinit var recipientCompanyAccountNumber: String
    lateinit var recipientBankBik: String
    lateinit var recipientBankAccountNumber: String
    lateinit var recipientBankName: String
}