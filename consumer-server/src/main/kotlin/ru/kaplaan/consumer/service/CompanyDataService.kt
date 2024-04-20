package ru.kaplaan.consumer.service

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.entity.data.ContactPerson

@Service
interface CompanyDataService {


    fun saveCompanyData(companyData: CompanyData): CompanyData

    fun updateCompanyData(companyData: CompanyData): CompanyData

    fun getCompanyDataByCompanyId(companyId: Long): CompanyData

    fun getContactPersonByCompanyId(companyId: Long): ContactPerson
}