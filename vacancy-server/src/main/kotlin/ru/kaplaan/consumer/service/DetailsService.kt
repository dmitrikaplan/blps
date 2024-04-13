package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.entity.data.UserData

@Service
interface DetailsService {


    fun saveCompanyData(companyData: CompanyData)

    fun getCompanyDataByCompanyName(companyName: String): CompanyData

    fun updateCompanyData(companyData: CompanyData): CompanyData

    fun saveUserData(userData: UserData)

    fun getUserDataByUsername(username: String): UserData

    fun updateUserData(userData: UserData): UserData
}