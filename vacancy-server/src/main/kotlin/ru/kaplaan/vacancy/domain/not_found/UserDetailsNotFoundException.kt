package ru.kaplaan.vacancy.domain.not_found

import ru.kaplaan.vacancy.domain.not_found.NotFoundException


class UserDetailsNotFoundException: NotFoundException("Дополнительные данные пользователя не найдены!") {

    override val message: String
        get() = super.message
}