package ru.kaplaan.consumer.repository.payment

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder

@Repository
interface PaymentOrderRepository: CrudRepository<PaymentOrder, Long> {

}