package ru.kaplaan.vacancy.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.kaplaan.vacancy.domain.entity.UserData

interface UserDataRepository: CrudRepository<UserData, Long> {
    @Query("select * from user_details where username = :username")
    fun findUserDataByUsername(@Param("username") username: String): UserData?
}