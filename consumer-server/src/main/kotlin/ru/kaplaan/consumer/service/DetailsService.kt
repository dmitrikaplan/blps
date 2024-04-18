package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.web.dto.data.UserDataDto

@Service
interface DetailsService {


    fun saveCompanyData(companyData: CompanyData): CompanyData

    fun updateCompanyData(companyData: CompanyData): CompanyData

    fun getCompanyDataByCompanyName(companyName: String): CompanyData

    fun saveUserData(userData: UserData): UserData

    fun updateUserData(userData: UserData): UserData

    fun getUserDataByUsername(username: String): UserData
}