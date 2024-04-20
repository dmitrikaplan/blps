package ru.kaplaan.authserver.web.dto.userInfo

import java.io.Serializable

data class ListUsernamesDto(val usernames: List<String>): Serializable
