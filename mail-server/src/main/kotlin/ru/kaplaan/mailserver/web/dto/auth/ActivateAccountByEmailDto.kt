package ru.kaplaan.mailserver.web.dto.auth

import kotlinx.serialization.Serializable


@Serializable
data class ActivateAccountByEmailDto(
    val emailTo: String,
    val username: String,
    val activationCode: String
)