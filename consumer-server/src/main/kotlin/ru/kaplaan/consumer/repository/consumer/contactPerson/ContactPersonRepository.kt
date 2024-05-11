package ru.kaplaan.consumer.repository.consumer.contactPerson

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import ru.kaplaan.consumer.domain.entity.data.ContactPerson

interface ContactPersonRepository: CrudRepository<ContactPerson, Long> {
    @Query("select * from contact_person where company_data_id = :companyDataId")
    fun findByCompanyDataId(companyDataId: Long): ContactPerson?
}