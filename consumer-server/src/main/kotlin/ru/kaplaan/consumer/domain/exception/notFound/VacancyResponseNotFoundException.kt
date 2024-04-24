package ru.kaplaan.consumer.domain.exception.notFound

class VacancyResponseNotFoundException: NotFoundException("Отклик на вакансию не найден!") {
    override val message: String
        get() = super.message
}