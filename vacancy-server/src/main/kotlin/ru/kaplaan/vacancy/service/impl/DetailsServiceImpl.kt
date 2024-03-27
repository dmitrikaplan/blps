package ru.kaplaan.vacancy.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.CompanyDetails
import ru.kaplaan.vacancy.domain.entity.UserDetails
import ru.kaplaan.vacancy.domain.not_found.CompanyDetailsNotFoundException
import ru.kaplaan.vacancy.domain.not_found.UserDetailsNotFoundException
import ru.kaplaan.vacancy.repository.CompanyDetailsRepository
import ru.kaplaan.vacancy.repository.UserDetailsRepository
import ru.kaplaan.vacancy.service.DetailsService

@Service
class DetailsServiceImpl(
    private val companyDetailsRepository: CompanyDetailsRepository,
    private val userDetailsRepository: UserDetailsRepository
): DetailsService {
    override fun saveCompanyDetails(companyDetails: CompanyDetails) {
        companyDetailsRepository.save(companyDetails)
    }

    override fun getCompanyDetailsByCompanyName(companyName: String): CompanyDetails =
        companyDetailsRepository.findCompanyDetailsByCompanyName(companyName)
            ?: throw CompanyDetailsNotFoundException()


    override fun saveUserDetails(userDetails: UserDetails) {
        userDetailsRepository.save(userDetails)
    }

    override fun getUserDetailsByUsername(username: String): UserDetails =
        userDetailsRepository.findUserDetailsByUsername(username)
            ?: throw UserDetailsNotFoundException()
}