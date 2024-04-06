package ru.kaplaan.vacancy.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.CompanyData
import ru.kaplaan.vacancy.domain.entity.UserData
import ru.kaplaan.vacancy.domain.exception.notFound.CompanyDataNotFoundException
import ru.kaplaan.vacancy.domain.exception.notFound.UserDetailsNotFoundException
import ru.kaplaan.vacancy.repository.CompanyDataRepository
import ru.kaplaan.vacancy.repository.UserDataRepository
import ru.kaplaan.vacancy.service.DetailsService

@Service
class DetailsServiceImpl(
    private val companyDataRepository: CompanyDataRepository,
    private val userDataRepository: UserDataRepository
): DetailsService {
    override fun saveCompanyData(companyData: CompanyData) {
        companyDataRepository.save(companyData)
    }

    override fun updateCompanyData(companyData: CompanyData): CompanyData =
        companyDataRepository.save(companyData)

    override fun getCompanyDataByCompanyName(companyName: String): CompanyData =
        companyDataRepository.findCompanyDataByCompanyName(companyName)
            ?: throw CompanyDataNotFoundException()


    override fun saveUserData(userData: UserData) {
        userDataRepository.save(userData)
    }

    override fun updateUserData(userData: UserData): UserData =
        userDataRepository.save(userData)

    override fun getUserDataByUsername(username: String): UserData =
        userDataRepository.findUserDataByUsername(username)
            ?: throw UserDetailsNotFoundException()
}