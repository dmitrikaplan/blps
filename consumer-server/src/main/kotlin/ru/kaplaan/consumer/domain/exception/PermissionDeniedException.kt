package ru.kaplaan.consumer.domain.exception

class PermissionDeniedException: RuntimeException("Невозможно получить доступ к ресурсу") {

    override val message: String
        get() = super.message!!
}