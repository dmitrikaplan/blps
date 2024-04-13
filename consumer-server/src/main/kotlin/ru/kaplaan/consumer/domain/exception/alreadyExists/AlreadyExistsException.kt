package ru.kaplaan.consumer.domain.exception.alreadyExists

abstract class AlreadyExistsException(override val message: String): RuntimeException(message)