package ru.kaplaan.authserver.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.authserver.domain.entity.user.User

@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun findByEmail(email: String): User?

    fun findByUsername(username: String): User?

    fun findUserByActivationCode(code: String): User?


    @Query("select * from users where user_id = :userId")
    fun findUserById(userId: Long): User?

}
