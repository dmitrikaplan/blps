package ru.kaplaan.consumer.web.dto.vacancy

enum class VacancyResponseStatus(val defaultComment: String, private val statusToString: String){
    REFUSED("Ваш отклик на вакансию отклонен", "Отклонен"),
    ACCEPTED("Вы приняты на работу", "Принят"),
    IN_PROCESSING("Мы приняли вашу заявку. Вернемся в ближайшее время с обратной связью", "В обработке");

    override fun toString(): String {
        return statusToString
    }
}