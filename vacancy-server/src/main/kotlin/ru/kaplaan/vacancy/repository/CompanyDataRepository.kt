package ru.kaplaan.vacancy.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.kaplaan.vacancy.domain.entity.CompanyData

@Repository
interface CompanyDataRepository: CrudRepository<CompanyData, Long> {

    @Query("select * from company_details where company_name = :company_ame")
    fun findCompanyDataByCompanyName(@Param("company_name") companyName: String): CompanyData?
}