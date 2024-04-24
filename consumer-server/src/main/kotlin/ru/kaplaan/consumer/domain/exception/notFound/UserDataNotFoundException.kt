package ru.kaplaan.consumer.domain.exception.notFound


class UserDataNotFoundException: NotFoundException("Дополнительные данные пользователя не найдены!") {

    override val message: String
        get() = super.message
}