package ru.kaplaan.consumer.service.impl

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.exception.notFound.UserNotFoundException
import ru.kaplaan.consumer.service.UserInfoService

@Service
class UserInfoServiceImpl(
    private val amqpTemplate: AmqpTemplate
): UserInfoService {

    @Value("\${rabbit.user-info.exchange-name}")
    private lateinit var userDataExchangeName: String

    @Value("\${rabbit.user-info.get-user-id-by-username.routing-key}")
    private lateinit var getUserIdRoutingKey: String

    @Value("\${rabbit.user-info.get-username-by-user-id.routing-key}")
    private lateinit var getUsernameRoutingKey: String

    @Value("\${rabbit.user-info.get-all-usernames-by-users-id.routing-key}")
    private lateinit var getAllUsernamesRoutingKey: String

    override fun getUserIdByUsername(username: String): Long {
        return amqpTemplate.convertSendAndReceive(
            userDataExchangeName,
            getUserIdRoutingKey,
            username
        ) as Long? ?: throw UserNotFoundException()
    }

    override fun getUsernameByUserId(userId: Long): String {
        return amqpTemplate.convertSendAndReceive(
            userDataExchangeName,
            getUsernameRoutingKey,
            userId
        ) as String? ?: throw UserNotFoundException()
    }

    override fun getAllUsernamesByUserIds(id: List<Long>): List<String> {
        return id.map { getUsernameByUserId(it) }
    }
}