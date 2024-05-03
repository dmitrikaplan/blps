package ru.kaplaan.consumer.service.data

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData

@Service
interface CompanyDataService {

    fun saveCompanyData(companyData: CompanyData): CompanyData

    fun updateCompanyData(companyData: CompanyData): CompanyData

    fun getCompanyDataByCompanyId(companyId: Long): CompanyData

    fun getCompanyEmailByCompanyId(companyId: Long): String
}