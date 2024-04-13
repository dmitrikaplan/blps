package ru.kaplaan.vacancy.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.kaplaan.vacancy.domain.entity.UserData

@Repository
interface UserDataRepository: CrudRepository<UserData, Long> {
    @Query("select * from user_data where username = :username")
    fun findUserDataByUsername(@Param("username") username: String): UserData?
}