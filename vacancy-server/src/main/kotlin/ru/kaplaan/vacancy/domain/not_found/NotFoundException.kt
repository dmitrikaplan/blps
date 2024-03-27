package ru.kaplaan.vacancy.domain.not_found

abstract class NotFoundException(override val message: String): RuntimeException()