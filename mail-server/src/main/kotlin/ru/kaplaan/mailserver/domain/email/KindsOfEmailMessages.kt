package ru.kaplaan.mailserver.domain.email

enum class KindsOfEmailMessages(val pathOfTemplate: String) {
    REGISTRATION_EMAIL("registration"),
    RECOVERY_EMAIL("recovery"),
    NOTIFY_ABOUT_UPDATE_VACANCY_RESPONSE_STATUS("notify_about_vacancy_response_status")
}
