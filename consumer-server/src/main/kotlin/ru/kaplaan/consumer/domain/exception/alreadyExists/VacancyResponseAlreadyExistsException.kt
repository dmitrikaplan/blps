package ru.kaplaan.consumer.domain.exception.alreadyExists

class VacancyResponseAlreadyExistsException: AlreadyExistsException("Отклик на вакансию уже существует!") {
    override val message: String
        get() = super.message
}