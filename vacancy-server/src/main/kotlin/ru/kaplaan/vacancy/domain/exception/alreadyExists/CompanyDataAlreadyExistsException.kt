package ru.kaplaan.vacancy.domain.exception.alreadyExists

class CompanyDataAlreadyExistsException: AlreadyExistsException("Данные о компании уже существуют!") {
    override val message: String
        get() = super.message
}