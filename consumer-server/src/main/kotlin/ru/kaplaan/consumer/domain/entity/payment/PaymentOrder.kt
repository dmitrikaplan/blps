package ru.kaplaan.consumer.domain.entity.payment

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("payment_order")
class PaymentOrder {

    @Id
    @Column("payment_order_id")
    var id: Long? = null
}