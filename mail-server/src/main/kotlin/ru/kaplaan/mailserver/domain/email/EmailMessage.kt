package ru.kaplaan.mailserver.domain.email

enum class EmailMessage(val pathOfTemplate: String, val subject: String) {
    REGISTRATION("registration", "Подтверждение регистрации"),
    RECOVERY("recovery", "Восстановление пароля"),
    VACANCY_RESPONSE_STATUS("vacancy_response_status", "Статус вашего отклика на вакансию изменен!"),
    PAYMENT_ORDER("payment_order", "Платежное поручение за указание услуг")
}
