package ru.kaplaan.vacancy.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import ru.kaplaan.vacancy.domain.entity.data.CompanyData

@Repository
interface CompanyDataRepository: CrudRepository<CompanyData, Long> {

    @Query("select * from company_data where company_id = :company_id")
    fun findCompanyDataByCompanyId(@Param("company_id") companyId: Long): CompanyData?
}