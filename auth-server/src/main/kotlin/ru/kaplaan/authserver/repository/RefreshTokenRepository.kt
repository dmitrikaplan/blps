package ru.kaplaan.authserver.repository

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.authserver.domain.entity.refreshToken.RefreshToken

@Repository
interface RefreshTokenRepository : CrudRepository<RefreshToken, Long>{

    @Query("select * from refresh_token where user_id = :userId")
    fun findRefreshTokenByUserId(userId: Long): RefreshToken?

    @Query("select * from refresh_token where token = :token")
    fun findRefreshTokenByToken(token: String): RefreshToken?

    @Modifying
    @Query("delete from users where token = :token")
    fun deleteByToken(token: String)

}
