package ru.kaplaan.consumer.domain.exception.notFound

class CompanyPaymentInfoNotFoundException: NotFoundException("Платежные данные компании не найдены!") {

    override val message: String
        get() = super.message
}