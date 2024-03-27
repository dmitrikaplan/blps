package ru.kaplaan.vacancy.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.kaplaan.vacancy.domain.entity.UserDetails

interface UserDetailsRepository: CrudRepository<UserDetails, Long> {
    @Query("select * from user_details where username = :username")
    fun findUserDetailsByUsername(@Param("username") username: String): UserDetails?
}