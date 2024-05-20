package ru.kaplaan.consumer.service.payment.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder
import ru.kaplaan.consumer.domain.exception.notFound.PaymentOrderNotFoundException
import ru.kaplaan.consumer.repository.billing.payment.PaymentOrderRepository
import ru.kaplaan.consumer.service.data.CompanyDataService
import ru.kaplaan.consumer.service.email.EmailService
import ru.kaplaan.consumer.service.payment.CompanyPaymentInfoService
import ru.kaplaan.consumer.service.payment.PaymentInfoService
import ru.kaplaan.consumer.service.payment.PaymentOrderService
import ru.kaplaan.consumer.web.mapper.payment.createPaymentOrder

@Service
class PaymentOrderServiceImpl(
    private val paymentOrderRepository: PaymentOrderRepository,
    private val paymentInfoService: PaymentInfoService,
    private val companyPaymentInfoService: CompanyPaymentInfoService,
    private val emailService: EmailService,
    private val companyDataService: CompanyDataService
): PaymentOrderService {

    @Value("\${page-size.payment-order}")
    private var pageSize: Int? = null

    @Transactional
    override fun generatePaymentOrder(companyId: Long, countOfPayments: Long): PaymentOrder {
        val payerPaymentInfo =  paymentInfoService.getByCompanyId(companyId)
        val recipientPaymentInfo = companyPaymentInfoService.get().apply {
            this.sum = this.sum!! * countOfPayments
        }

        return (payerPaymentInfo to recipientPaymentInfo).createPaymentOrder().also {
            paymentOrderRepository.save(it)
        }
    }

    @Transactional
    override fun getPaymentOrdersByCompanyId(companyId: Long, pageNumber: Int): List<PaymentOrder> {
        return paymentOrderRepository.findPaymentOrdersByCompanyId(companyId, PageRequest.of(pageNumber, pageSize!!))
    }

    @Transactional
    override fun getPaymentOrderById(id: Long): PaymentOrder {
        return paymentOrderRepository.findByIdOrNull(id)
            ?: throw PaymentOrderNotFoundException()
    }


    @Transactional
    override fun setPaymentOrderCompleted(paymentOrderId: Long) {
        paymentOrderRepository.setPaymentOrderIsCompleted(paymentOrderId)

        getPaymentOrderById(paymentOrderId).let { paymentOrder ->
            companyDataService.getCompanyEmailByCompanyId(paymentOrder.payerCompanyId!!).also { email ->
                emailService.sendInfoAboutSuccessPayment(email, paymentOrder.payerCompanyName, paymentOrderId)
            }

        }

    }
}