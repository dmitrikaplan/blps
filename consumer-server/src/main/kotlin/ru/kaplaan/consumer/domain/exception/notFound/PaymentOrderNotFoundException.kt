package ru.kaplaan.consumer.domain.exception.notFound

class PaymentOrderNotFoundException: NotFoundException("Платежное поручение не найдено!") {
    override val message: String
        get() = super.message
}