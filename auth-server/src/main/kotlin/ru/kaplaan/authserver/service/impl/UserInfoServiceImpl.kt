package ru.kaplaan.authserver.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.exception.user.UserNotFoundException
import ru.kaplaan.authserver.repository.UserRepository
import ru.kaplaan.authserver.service.UserInfoService

@Service
class UserInfoServiceImpl(
    private val userRepository: UserRepository
): UserInfoService {

    override fun getUsernameByUserId(userId: Long): String =
        userRepository.findUserById(userId)?.username
            ?: throw UserNotFoundException()
}