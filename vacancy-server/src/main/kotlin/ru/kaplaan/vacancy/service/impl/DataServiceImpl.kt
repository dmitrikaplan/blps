package ru.kaplaan.vacancy.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.data.CompanyData
import ru.kaplaan.vacancy.domain.entity.data.UserData
import ru.kaplaan.vacancy.domain.exception.alreadyExists.CompanyDataAlreadyExistsException
import ru.kaplaan.vacancy.domain.exception.alreadyExists.UserDataAlreadyExistsException
import ru.kaplaan.vacancy.domain.exception.notFound.CompanyDataNotFoundException
import ru.kaplaan.vacancy.domain.exception.notFound.UserDetailsNotFoundException
import ru.kaplaan.vacancy.repository.CompanyDataRepository
import ru.kaplaan.vacancy.repository.UserDataRepository
import ru.kaplaan.vacancy.service.DetailsService
import ru.kaplaan.vacancy.service.UserService

@Service
class DataServiceImpl(
    private val companyDataRepository: CompanyDataRepository,
    private val userDataRepository: UserDataRepository,
    private val userService: UserService
): DetailsService {

    override fun saveCompanyData(companyData: CompanyData) {
        val companyId = userService.getUserIdByUsername(companyData.companyName)
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
                companyId = userService.getUserIdByUsername(companyData.companyName)
            }
        )

    override fun getCompanyDataByCompanyName(companyName: String): CompanyData =
        userService.getUserIdByUsername(companyName).let { companyId ->
            companyDataRepository.findCompanyDataByCompanyId(companyId)
                ?: throw CompanyDataNotFoundException()
        }



    override fun saveUserData(userData: UserData) {
        val userId = userService.getUserIdByUsername(userData.username)
        userDataRepository.findUserDataByUserId(userId)?.let {
            throw UserDataAlreadyExistsException()
        } ?: userDataRepository.save(
            userData.apply {
                this.userId = userId
            }
        )
    }

    override fun updateUserData(userData: UserData): UserData =
        userService.getUserIdByUsername(userData.username).let { userId ->
            userDataRepository.save(
                userData.apply {
                    this.userId = userId
                }
            )
        }


    override fun getUserDataByUsername(username: String): UserData =
        userService.getUserIdByUsername(username).let { userId ->
            userDataRepository.findUserDataByUserId(userId)
                ?: throw UserDetailsNotFoundException()
        }

}