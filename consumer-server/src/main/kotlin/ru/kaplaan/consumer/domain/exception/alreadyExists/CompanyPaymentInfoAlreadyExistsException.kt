package ru.kaplaan.consumer.domain.exception.alreadyExists

class CompanyPaymentInfoAlreadyExistsException: AlreadyExistsException("Платежные данные компании уже существуют!") {

    override val message: String
        get() = super.message
}