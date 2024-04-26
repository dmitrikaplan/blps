package ru.kaplaan.consumer.domain.entity.payment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("payment_order")
class PaymentOrder {

    @Id
    @Column("payment_order_id")
    var id: Long? = null

    @Column("payer_inn")
    lateinit var payerInn: String

    @Column("payer_kpp")
    lateinit var payerKpp: String

    @Column("payer_company_name")
    lateinit var payerCompanyName: String

    @Column("payer_company_account_number")
    lateinit var payerCompanyAccountNumber: String

    @Column("sum")
    var sum: Long? = null

    @Column("payer_bank_bik")
    lateinit var payerBankBik: String

    @Column("payer_bank_account_number")
    lateinit var payerBankAccountNumber: String

    @Column("payer_bank_name")
    lateinit var payerBankName: String

    @Column("payer_company_id")
    var payerCompanyId: Long? = null

    @Column("recipient_inn")
    lateinit var recipientInn: String

    @Column("recipient_kpp")
    lateinit var recipientKpp: String

    @Column("recipient_company_name")
    lateinit var recipientCompanyName: String

    @Column("recipient_company_account_number")
    lateinit var recipientCompanyAccountNumber: String

    @Column("recipient_bank_bik")
    lateinit var recipientBankBik: String

    @Column("recipient_bank_account_number")
    lateinit var recipientBankAccountNumber: String

    @Column("recipient_bank_name")
    lateinit var recipientBankName: String

    @Column("creation_date")
    lateinit var creationDate: LocalDate

    @Column("is_completed")
    var isCompleted: Boolean? = null

    @Column("purpose_of_payment")
    lateinit var purposeOfPayment: String
}