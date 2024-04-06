package ru.kaplaan.vacancy.service

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.CompanyData
import ru.kaplaan.vacancy.domain.entity.UserData

@Service
interface DetailsService {


    fun saveCompanyData(companyData: CompanyData)

    fun getCompanyDataByCompanyName(companyName: String): CompanyData

    fun updateCompanyData(companyData: CompanyData): CompanyData

    fun saveUserData(userData: UserData)

    fun getUserDataByUsername(username: String): UserData

    fun updateUserData(userData: UserData): UserData
}