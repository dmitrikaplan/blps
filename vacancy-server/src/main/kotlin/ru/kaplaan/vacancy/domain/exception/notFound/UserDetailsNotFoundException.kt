package ru.kaplaan.vacancy.domain.exception.notFound


class UserDetailsNotFoundException: NotFoundException("Дополнительные данные пользователя не найдены!") {

    override val message: String
        get() = super.message
}