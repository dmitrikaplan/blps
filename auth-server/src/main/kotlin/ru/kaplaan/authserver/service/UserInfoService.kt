package ru.kaplaan.authserver.service

import org.springframework.stereotype.Service
import ru.kaplaan.authserver.web.dto.userInfo.ListIdDto
import ru.kaplaan.authserver.web.dto.userInfo.ListUsernamesDto

@Service
interface UserInfoService {

    fun getUserIdByUsername(username: String): Long?

    fun getUsernameByUserId(userId: Long): String?

    fun getUsernamesByUsersId(userIds: ListIdDto): ListUsernamesDto
}