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
import ru.kaplaan.consumer.service.UserInfoService

@Service
class DataServiceImpl(
    private val companyDataRepository: CompanyDataRepository,
    private val userDataRepository: UserDataRepository,
    private val userInfoService: UserInfoService
): DetailsService {

    override fun saveCompanyData(companyData: CompanyData): CompanyData {
        val companyId = userInfoService.getUserIdByUsername(companyData.companyName)
        return companyDataRepository.findCompanyDataByCompanyId(companyId)?.let {
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
                companyId = userInfoService.getUserIdByUsername(companyData.companyName)
            }
        )

    override fun getCompanyDataByCompanyName(companyName: String): CompanyData =
        userInfoService.getUserIdByUsername(companyName).let { companyId ->
            companyDataRepository.findCompanyDataByCompanyId(companyId)
                ?: throw CompanyDataNotFoundException()
        }



    override fun saveUserData(userData: UserData): UserData {
        val userId = userInfoService.getUserIdByUsername(userData.username)
        return userDataRepository.findUserDataByUserId(userId)?.let {
            throw UserDataAlreadyExistsException()
        } ?: userDataRepository.save(
            userData.apply {
                this.userId = userId
            }
        )
    }

    override fun updateUserData(userData: UserData): UserData =
        userInfoService.getUserIdByUsername(userData.username).let { userId ->
            userDataRepository.save(
                userData.apply {
                    this.userId = userId
                }
            )
        }


    override fun getUserDataByUsername(username: String): UserData =
        userInfoService.getUserIdByUsername(username).let { userId ->
            userDataRepository.findUserDataByUserId(userId)
                ?: throw UserDetailsNotFoundException()
        }

}