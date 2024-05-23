package ru.kaplaan.authserver.domain.exception.user


class UserNotFoundByActivationCodeException:
    UserException("Пользователь с таким кодом активации не найден!")