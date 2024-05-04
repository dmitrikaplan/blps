package ru.kaplaan.consumer.repository.billing.payment

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.payment.CompanyPaymentInfo

@Repository
interface CompanyPaymentInfoRepository: CrudRepository<CompanyPaymentInfo, Long> {

    @Query("select * from company_payment_info limit 1")
    fun findFirst(): CompanyPaymentInfo?
}