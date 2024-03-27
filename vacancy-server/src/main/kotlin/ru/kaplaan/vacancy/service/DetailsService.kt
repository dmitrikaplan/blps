package ru.kaplaan.vacancy.service

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.CompanyDetails
import ru.kaplaan.vacancy.domain.entity.UserDetails

@Service
interface DetailsService {


    fun saveCompanyDetails(companyDetails: CompanyDetails)

    fun getCompanyDetailsByCompanyName(companyName: String): CompanyDetails

    fun saveUserDetails(userDetails: UserDetails)

    fun getUserDetailsByUsername(username: String): UserDetails
}