package ru.kaplaan.vacancy.domain.exception.notFound

abstract class NotFoundException(override val message: String): RuntimeException()