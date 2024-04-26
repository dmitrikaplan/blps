package ru.kaplaan.consumer.service.accountant

import org.springframework.stereotype.Service

@Service
interface AccountantService {

    fun sendPaymentOrder(companyId: Long)

}