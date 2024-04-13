package ru.kaplaan.consumer.domain.exception.notFound

abstract class NotFoundException(override val message: String): RuntimeException()