package ru.kaplaan.consumer.domain.exception.notFound

class UserNotFoundException: NotFoundException("Пользователь не найден!") {

    override val message: String
        get() = super.message
}