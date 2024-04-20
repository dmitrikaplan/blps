package ru.kaplaan.authserver.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.repository.UserRepository
import ru.kaplaan.authserver.service.UserInfoService
import ru.kaplaan.authserver.web.dto.userInfo.ListIdDto
import ru.kaplaan.authserver.web.dto.userInfo.ListUsernamesDto

@Service
class UserInfoServiceImpl(
    private val userRepository: UserRepository
): UserInfoService {

    override fun getUserIdByUsername(username: String): Long? =
        userRepository.findByUsername(username)?.id

    override fun getUsernameByUserId(userId: Long): String? =
        userRepository.findUserById(userId)?.username

    override fun getUsernamesByUsersId(userIds: ListIdDto): ListUsernamesDto =
        userIds.list.map {
            getUsernameByUserId(it) ?: return ListUsernamesDto(listOf())
        }.let {
            ListUsernamesDto(it)
        }
}