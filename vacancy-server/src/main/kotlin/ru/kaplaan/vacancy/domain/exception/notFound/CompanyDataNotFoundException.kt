package ru.kaplaan.vacancy.domain.exception.notFound

class CompanyDataNotFoundException: NotFoundException("Дополнительные данные о компании не найдены!") {
    override val message: String
        get() = super.message

}