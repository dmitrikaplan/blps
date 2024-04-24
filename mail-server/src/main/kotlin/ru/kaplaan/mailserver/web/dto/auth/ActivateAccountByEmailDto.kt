package ru.kaplaan.mailserver.web.dto.auth

import kotlinx.serialization.Serializable


@Serializable
data class ActivateAccountByEmailDto(
    val emailTo: String,
    val login: String,
    val activationCode: String
)