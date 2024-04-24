package ru.kaplaan.mailserver.domain.email

enum class KindsOfSubjects(val subject: String) {
    REGISTRATION("Подтверждение регистрации"),
    PASSWORD_RECOVERY("Восстановление пароля"),
    NOTIFY_ABOUT_UPDATE_VACANCY_RESPONSE_STATUS("Статус вашего отклика на вакансию изменен!")
}
