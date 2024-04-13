package ru.kaplaan.vacancy.domain.exception.alreadyExists

class UserDataAlreadyExistsException: AlreadyExistsException("Данные о пользователе уже существуют!") {

    override val message: String
        get() = super.message
}