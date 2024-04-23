package ru.kaplaan.authserver.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.authserver.domain.entity.user.User

@Repository
interface UserRepository : CrudRepository<User, Long> {

    @Query("select * from users where email = :email")
    fun findByEmail(email: String): User?

    @Query("select * from users where username = :username")
    fun findByUsername(username: String): User?

    @Query("select * from users where activation_code = :code")
    fun findUserByActivationCode(code: String): User?

    @Query("select * from users where user_id = :userId")
    fun findUserById(userId: Long): User?

    @Query("select user_id from users where role = 'ROLE_ADMIN'")
    fun findUserIdByRoleAdmin(): Long?
}
