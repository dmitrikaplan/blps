package ru.kaplaan.mailserver.domain.email

enum class KindsOfEmailMessages(val pathOfTemplate: String) {
    REGISTRATION_EMAIL("registration"),
    RECOVERY_EMAIL("recovery")
}
