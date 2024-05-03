package ru.kaplaan.consumer.service.email

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.domain.entity.payment.PaymentOrder
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse

@Service
interface EmailService {

    fun sendVacancyResponseMail(vacancyResponse: VacancyResponse, vacancy: Vacancy, userData: UserData)

    fun sendPaymentOrder(email: String, paymentOrder: PaymentOrder)

    fun sendInfoAboutSuccessPayment(email: String, companyName: String, paymentOrderId: Long)
}