package ru.kaplaan.consumer.web.dto.vacancy

enum class VacancyResponseStatus(val defaultComment: String){
    REFUSED(""),
    ACCEPTED(""),
    IN_PROCESSING("Мы приняли вашу заявку. Вернемся в ближайшее время с обратной связью")
}