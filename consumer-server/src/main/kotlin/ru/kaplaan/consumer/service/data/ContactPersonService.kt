package ru.kaplaan.consumer.service.data

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.ContactPerson

@Service
interface ContactPersonService {
    fun getByCompanyDataId(companyDataId: Long): ContactPerson

}