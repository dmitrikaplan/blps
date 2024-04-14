package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service

@Service
interface UserInfoService {

    fun getUserIdByUsername(username: String): Long

    fun getUsernameByUserId(userId: Long): String

    fun getAllUsernamesByUserIds(id: List<Long>): List<String>
}