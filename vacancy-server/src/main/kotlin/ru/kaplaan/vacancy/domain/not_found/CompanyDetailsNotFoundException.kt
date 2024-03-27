package ru.kaplaan.vacancy.domain.not_found

class CompanyDetailsNotFoundException: NotFoundException("Дополнительные данные о компании не найдены!") {
    override val message: String
        get() = super.message

}