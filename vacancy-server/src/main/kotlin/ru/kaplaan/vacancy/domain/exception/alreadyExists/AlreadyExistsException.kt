package ru.kaplaan.vacancy.domain.exception.alreadyExists

abstract class AlreadyExistsException(override val message: String): RuntimeException(message)