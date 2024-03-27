package ru.kaplaan.authserver.domain.entity.refreshToken

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("refresh_token")
class RefreshToken {

    @Id
    @Column("refresh_token_id")
    var id: Long? = null

    lateinit var token: String

    var userId: Long? = null
}
