package ru.kaplaan.authserver.web.dto

import org.springframework.http.HttpStatus

data class Problem(
    val status: HttpStatus,
    val detail: String
){
    val properties: MutableMap<String, Any?> = mutableMapOf()
}