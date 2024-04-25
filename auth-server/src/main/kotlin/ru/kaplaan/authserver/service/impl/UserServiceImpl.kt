package ru.kaplaan.authserver.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.domain.entity.user.User
import ru.kaplaan.authserver.domain.exception.user.UserNotFoundByActivationCodeException
import ru.kaplaan.authserver.domain.exception.user.UserNotFoundException
import ru.kaplaan.authserver.repository.UserRepository
import ru.kaplaan.authserver.service.UserService

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun save(user: User): User =
        userRepository.save(user)

    override fun getUserByActivationCode(activationCode: String): User {
        return userRepository.findUserByActivationCode(activationCode)
            ?: throw UserNotFoundByActivationCodeException()
    }

    override fun getUserByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override fun getUsernameByUserId(userId: Long): String =
        userRepository.findUserById(userId)?.username
            ?: throw UserNotFoundException()

    override fun getUserById(userId: Long): User =
         userRepository.findUserById(userId)
            ?: throw UserNotFoundException()
}