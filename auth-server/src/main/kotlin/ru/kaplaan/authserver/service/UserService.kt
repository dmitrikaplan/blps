package ru.kaplaan.authserver.service

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.entity.user.User

@Service
interface UserService{

    fun save(user: User): User

    fun getUserByActivationCode(activationCode: String): User

    fun getUserByUsername(username: String): User?

    fun getUsernameByUserId(userId: Long): String

    fun getUserById(userId: Long): User
}