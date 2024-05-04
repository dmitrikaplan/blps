package ru.kaplaan.consumer.repository.billing.payment

import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder

@Repository
interface PaymentOrderRepository: CrudRepository<PaymentOrder, Long> {

    @Query("select * from payment_order where payer_company_id = :companyId")
    fun findPaymentOrdersByCompanyId(companyId: Long, pageable: Pageable): List<PaymentOrder>


    @Modifying
    @Query("update payment_order set is_completed = true where payment_order_id = :paymentOrderId")
    fun setPaymentOrderIsCompleted(paymentOrderId: Long)
}