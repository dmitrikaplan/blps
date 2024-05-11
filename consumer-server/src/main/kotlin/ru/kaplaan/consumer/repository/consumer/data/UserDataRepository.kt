package ru.kaplaan.consumer.repository.consumer.data

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.data.UserData

@Repository
interface UserDataRepository: CrudRepository<UserData, Long> {
    @Query("select * from user_data where user_id = :user_id")
    fun findUserDataByUserId(@Param("user_id") userId: Long): UserData?

    @Query("select user_data_id from user_data where user_id =:userId")
    fun findUserDataIdByUserId(userId: Long): Long?
}