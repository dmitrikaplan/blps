package ru.kaplaan.authserver.web.dto.email

import java.io.Serializable

data class ActivateAccountByEmailDto(
    val emailTo: String,
    val login: String,
    val activationCode: String
): Serializable