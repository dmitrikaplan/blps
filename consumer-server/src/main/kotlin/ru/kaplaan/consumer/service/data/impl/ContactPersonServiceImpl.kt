package ru.kaplaan.consumer.service.data.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.ContactPerson
import ru.kaplaan.consumer.domain.exception.notFound.ContactPersonNotFoundException
import ru.kaplaan.consumer.repository.contactPerson.ContactPersonRepository
import ru.kaplaan.consumer.service.data.ContactPersonService

@Service
class ContactPersonServiceImpl(
    private val contactPersonRepository: ContactPersonRepository,
): ContactPersonService {

    override fun getByCompanyDataId(companyDataId: Long): ContactPerson {
        return contactPersonRepository.findByCompanyDataId(companyDataId)
            ?: throw ContactPersonNotFoundException()
    }


}