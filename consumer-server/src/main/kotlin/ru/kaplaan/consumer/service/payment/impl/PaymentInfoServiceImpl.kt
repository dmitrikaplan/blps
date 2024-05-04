package ru.kaplaan.consumer.service.payment.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kaplaan.consumer.domain.entity.payment.PaymentInfo
import ru.kaplaan.consumer.domain.exception.alreadyExists.PaymentInfoAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.PaymentInfoNotFoundException
import ru.kaplaan.consumer.repository.billing.payment.PaymentInfoRepository
import ru.kaplaan.consumer.service.payment.PaymentInfoService

@Service
class PaymentInfoServiceImpl(
    private val paymentInfoRepository: PaymentInfoRepository
): PaymentInfoService {

    @Transactional
    override fun save(paymentInfo: PaymentInfo): PaymentInfo {
        if(paymentInfoRepository.existsByCompanyId(paymentInfo.companyId!!))
            throw PaymentInfoAlreadyExistsException()

        return paymentInfoRepository.save(paymentInfo)
    }

    @Transactional
    override fun update(paymentInfo: PaymentInfo): PaymentInfo {
        if(!paymentInfoRepository.existsByCompanyId(paymentInfo.companyId!!))
            throw PaymentInfoNotFoundException()

        return paymentInfoRepository.save(paymentInfo)
    }

    @Transactional
    override fun getByCompanyId(companyId: Long): PaymentInfo {
        return paymentInfoRepository.findByCompanyId(companyId)
    }

    @Transactional
    override fun existsByCompanyId(companyId: Long): Boolean {
        return paymentInfoRepository.existsByCompanyId(companyId)
    }
}