package ru.kaplaan.consumer.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.domain.exception.alreadyExists.CompanyDataAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.alreadyExists.UserDataAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.CompanyDataNotFoundException
import ru.kaplaan.consumer.domain.exception.notFound.UserDetailsNotFoundException
import ru.kaplaan.consumer.repository.CompanyDataRepository
import ru.kaplaan.consumer.repository.UserDataRepository
import ru.kaplaan.consumer.service.DetailsService
import ru.kaplaan.consumer.service.UserServiceInfo

@Service
class DataServiceImpl(
    private val companyDataRepository: CompanyDataRepository,
    private val userDataRepository: UserDataRepository,
    private val userServiceInfo: UserServiceInfo
): DetailsService {

    override fun saveCompanyData(companyData: CompanyData) {
        val companyId = userServiceInfo.getUserIdByUsername(companyData.companyName)
        companyDataRepository.findCompanyDataByCompanyId(companyId)?.let {
            throw CompanyDataAlreadyExistsException()
        } ?: companyDataRepository.save(
                companyData.apply {
                    this.companyId = companyId
                }
            )
    }

    override fun updateCompanyData(companyData: CompanyData): CompanyData =
        companyDataRepository.save(
            companyData.apply {
                companyId = userServiceInfo.getUserIdByUsername(companyData.companyName)
            }
        )

    override fun getCompanyDataByCompanyName(companyName: String): CompanyData =
        userServiceInfo.getUserIdByUsername(companyName).let { companyId ->
            companyDataRepository.findCompanyDataByCompanyId(companyId)
                ?: throw CompanyDataNotFoundException()
        }



    override fun saveUserData(userData: UserData) {
        val userId = userServiceInfo.getUserIdByUsername(userData.username)
        userDataRepository.findUserDataByUserId(userId)?.let {
            throw UserDataAlreadyExistsException()
        } ?: userDataRepository.save(
            userData.apply {
                this.userId = userId
            }
        )
    }

    override fun updateUserData(userData: UserData): UserData =
        userServiceInfo.getUserIdByUsername(userData.username).let { userId ->
            userDataRepository.save(
                userData.apply {
                    this.userId = userId
                }
            )
        }


    override fun getUserDataByUsername(username: String): UserData =
        userServiceInfo.getUserIdByUsername(username).let { userId ->
            userDataRepository.findUserDataByUserId(userId)
                ?: throw UserDetailsNotFoundException()
        }

}