package ru.kaplaan.consumer.service.data

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.UserData

@Service
interface UserDataService {
    fun saveUserData(userData: UserData): UserData

    fun updateUserData(userData: UserData): UserData

    fun getUserDataByUserId(userId: Long): UserData
}