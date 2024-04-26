package ru.kaplaan.consumer.domain.exception.notFound

class PaymentInfoNotFoundException: NotFoundException("Информация о плательщике не найдена!") {

    override val message: String
        get() = super.message
}