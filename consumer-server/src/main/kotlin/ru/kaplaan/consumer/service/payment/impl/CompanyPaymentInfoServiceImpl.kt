package ru.kaplaan.consumer.service.payment.impl

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.payment.CompanyPaymentInfo
import ru.kaplaan.consumer.domain.exception.alreadyExists.CompanyPaymentInfoAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.CompanyPaymentInfoNotFoundException
import ru.kaplaan.consumer.repository.payment.CompanyPaymentInfoRepository
import ru.kaplaan.consumer.service.payment.CompanyPaymentInfoService

@Service
class CompanyPaymentInfoServiceImpl(
    private val companyPaymentInfoRepository: CompanyPaymentInfoRepository
): CompanyPaymentInfoService {

    private val log = LoggerFactory.getLogger(javaClass)

    override fun save(companyPaymentInfo: CompanyPaymentInfo): CompanyPaymentInfo {
        return companyPaymentInfoRepository.findFirst()?.let {
            throw CompanyPaymentInfoAlreadyExistsException()
        } ?: companyPaymentInfoRepository.save(companyPaymentInfo)
    }

    override fun get(): CompanyPaymentInfo {
        return companyPaymentInfoRepository.findFirst()
            ?: run{
                log.error("Платежные данные компании не найдены!")
                throw CompanyPaymentInfoNotFoundException()
            }
    }
}