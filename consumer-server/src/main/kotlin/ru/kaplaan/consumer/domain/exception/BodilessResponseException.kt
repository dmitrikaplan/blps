package ru.kaplaan.consumer.domain.exception

class BodilessResponseException: RuntimeException("Ответ внутреннего сервиса не содержит тела!") {
    override val message: String
        get() = super.message!!
}