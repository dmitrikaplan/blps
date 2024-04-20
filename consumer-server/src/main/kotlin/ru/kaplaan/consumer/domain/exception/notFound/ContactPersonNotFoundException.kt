package ru.kaplaan.consumer.domain.exception.notFound

class ContactPersonNotFoundException: NotFoundException("Контактное лицо не найдено!") {
    override val message: String
        get() = super.message
}