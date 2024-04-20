package ru.kaplaan.authserver.service

import org.springframework.stereotype.Service

@Service
interface UserInfoService {

    fun getUserIdByUsername(username: String): Long?

    fun getUsernameByUserId(userId: Long): String?
}