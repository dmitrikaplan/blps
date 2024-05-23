package ru.kaplaan.consumer.domain.exception

class PermissionDeniedException(message: String? = null): RuntimeException(message ?: "Невозможно получить доступ к ресурсу") {
    override val message: String
        get() = super.message!!
}