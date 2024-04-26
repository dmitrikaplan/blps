package ru.kaplaan.consumer.service.payer.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.payment.PaymentInfo
import ru.kaplaan.consumer.domain.exception.alreadyExists.PaymentInfoAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.PaymentInfoNotFoundException
import ru.kaplaan.consumer.repository.payment.PaymentInfoRepository
import ru.kaplaan.consumer.service.payer.PaymentInfoService

@Service
class PaymentInfoServiceImpl(
    private val paymentInfoRepository: PaymentInfoRepository
): PaymentInfoService {
    override fun save(paymentInfo: PaymentInfo): PaymentInfo {
        if(paymentInfoRepository.existsByCompanyId(paymentInfo.companyId!!))
            throw PaymentInfoAlreadyExistsException()

        return paymentInfoRepository.save(paymentInfo)
    }

    override fun update(paymentInfo: PaymentInfo): PaymentInfo {
        if(!paymentInfoRepository.existsByCompanyId(paymentInfo.companyId!!))
            throw PaymentInfoNotFoundException()

        return paymentInfoRepository.save(paymentInfo)
    }

    override fun getByCompanyId(companyId: Long): PaymentInfo {
        return paymentInfoRepository.findByCompanyId(companyId)
    }

    override fun existsByCompanyId(companyId: Long): Boolean {
        return paymentInfoRepository.existsByCompanyId(companyId)
    }
}