package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service

@Service
interface UserService {

    fun getUserIdByUsername(username: String): Long

    fun getAllUsernamesByUserIds(id: List<Long>): List<String>
}