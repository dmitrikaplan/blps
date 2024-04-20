package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.entity.data.UserData

@Service
interface DetailsService {


    fun saveCompanyData(companyData: CompanyData): CompanyData

    fun updateCompanyData(companyData: CompanyData): CompanyData

    fun getCompanyDataByCompanyId(companyId: Long): CompanyData

    fun saveUserData(userData: UserData): UserData

    fun updateUserData(userData: UserData): UserData

    fun getUserDataByUserId(userId: Long): UserData
}