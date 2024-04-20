package ru.kaplaan.authserver.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.exception.user.UserNotFoundException
import ru.kaplaan.authserver.repository.UserRepository
import ru.kaplaan.authserver.service.UserInfoService

@Service
class UserInfoServiceImpl(
    private val userRepository: UserRepository
): UserInfoService {

    override fun getUserIdByUsername(username: String): Long? =
        userRepository.findByUsername(username)?.id

    override fun getUsernameByUserId(userId: Long): String? =
        userRepository.findUserById(userId)?.username

    override fun getUsernamesByUsersId(userIds: List<Long>): List<String?> =
        userIds.map {
            getUsernameByUserId(it)
        }
}