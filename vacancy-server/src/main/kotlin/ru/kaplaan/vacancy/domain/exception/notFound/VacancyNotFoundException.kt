package ru.kaplaan.vacancy.domain.exception.notFound

class  VacancyNotFoundException: NotFoundException("Невозможно найти вакансию!") {

    override val message: String
        get() = super.message
}