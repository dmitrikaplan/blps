package ru.kaplaan.consumer.service.payment.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder
import ru.kaplaan.consumer.repository.payment.PaymentOrderRepository
import ru.kaplaan.consumer.service.payment.CompanyPaymentInfoService
import ru.kaplaan.consumer.service.payment.PaymentInfoService
import ru.kaplaan.consumer.service.payment.PaymentOrderService
import ru.kaplaan.consumer.web.mapper.payment.createPaymentOrder

@Service
class PaymentOrderServiceImpl(
    private val paymentOrderRepository: PaymentOrderRepository,
    private val paymentInfoService: PaymentInfoService,
    private val companyPaymentInfoService: CompanyPaymentInfoService
): PaymentOrderService {

    @Value("\${page-size.payment-order}")
    private var pageSize: Int? = null

    override fun generatePaymentOrder(companyId: Long): PaymentOrder {
        val payerPaymentInfo =  paymentInfoService.getByCompanyId(companyId)
        val recipientPaymentInfo = companyPaymentInfoService.get()

        return (payerPaymentInfo to recipientPaymentInfo).createPaymentOrder().also {
            paymentOrderRepository.save(it)
        }
    }

    override fun getPaymentOrdersByCompanyId(companyId: Long, pageNumber: Int): List<PaymentOrder> {
        return paymentOrderRepository.findPaymentOrdersByCompanyId(companyId, PageRequest.of(pageNumber, pageSize!!))
    }
}