package ru.kaplaan.authserver.service

import org.springframework.stereotype.Service

@Service
interface UserInfoService {

    fun getUsernameByUserId(userId: Long): String
}