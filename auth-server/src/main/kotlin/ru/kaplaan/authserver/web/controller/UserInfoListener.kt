package ru.kaplaan.authserver.web.controller

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.kaplaan.authserver.service.UserInfoService

@Component
@EnableRabbit
class UserInfoListener(
    private val userInfoService: UserInfoService
) {

    @RabbitListener(queues = ["get-user-id-by-username"])
    fun getUserIdByUsername(username: String): Long? =
        userInfoService.getUserIdByUsername(username)

    @RabbitListener(queues = ["get-username-by-user-id"])
    fun getUsernameByUserId(userId: Long): String? =
        userInfoService.getUsernameByUserId(userId)

    @RabbitListener(queues = ["get-all-usernames-by-users-id"])
    fun getUsernamesByUserIds(ids: List<Long>): List<String?> =
        userInfoService.getUsernamesByUsersId(ids)
}