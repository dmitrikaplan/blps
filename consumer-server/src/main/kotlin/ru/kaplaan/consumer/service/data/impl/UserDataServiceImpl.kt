package ru.kaplaan.consumer.service.data.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.domain.exception.alreadyExists.UserDataAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.UserDataNotFoundException
import ru.kaplaan.consumer.repository.data.UserDataRepository
import ru.kaplaan.consumer.service.data.UserDataService

@Service
class UserDataServiceImpl(
    private val userDataRepository: UserDataRepository
): UserDataService {

    override fun saveUserData(userData: UserData): UserData {
        return userDataRepository.findUserDataByUserId(userData.userId!!)?.let {
            throw UserDataAlreadyExistsException()
        } ?: userDataRepository.save(userData)
    }

    override fun updateUserData(userData: UserData): UserData =
        userDataRepository.save(
            userData.apply {
                this.id = userDataRepository.findUserDataIdByUserId(userId!!)
            }
        )


    override fun getUserDataByUserId(userId: Long): UserData =
        userDataRepository.findUserDataByUserId(userId)
            ?: throw UserDataNotFoundException()
}