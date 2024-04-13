package ru.kaplaan.vacancy.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.kaplaan.vacancy.domain.entity.data.UserData

@Repository
interface UserDataRepository: CrudRepository<UserData, Long> {
    @Query("select * from user_data where user_id = :user_id")
    fun findUserDataByUserId(@Param("user_id") userId: Long): UserData?
}