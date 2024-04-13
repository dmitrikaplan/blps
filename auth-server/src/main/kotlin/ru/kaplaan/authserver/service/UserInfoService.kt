package ru.kaplaan.authserver.service

import org.springframework.stereotype.Service

@Service
interface UserInfoService {

    fun getUserIdByUsername(username: String): Long

    fun getUsernameByUserId(userId: Long): String

    fun getUsernamesByUserIds(userIds: List<Long>): List<String>
}