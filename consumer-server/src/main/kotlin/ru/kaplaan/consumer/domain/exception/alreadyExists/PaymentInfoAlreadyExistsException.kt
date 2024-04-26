package ru.kaplaan.consumer.domain.exception.alreadyExists

class PaymentInfoAlreadyExistsException: AlreadyExistsException("Информация о плательщике уже существует!") {

    override val message: String
        get() = super.message
}