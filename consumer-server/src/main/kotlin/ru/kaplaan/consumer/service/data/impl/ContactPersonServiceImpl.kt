package ru.kaplaan.consumer.service.data.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.kaplaan.consumer.domain.entity.data.ContactPerson
import ru.kaplaan.consumer.domain.exception.notFound.ContactPersonNotFoundException
import ru.kaplaan.consumer.repository.consumer.contactPerson.ContactPersonRepository
import ru.kaplaan.consumer.service.data.ContactPersonService

@Service
class ContactPersonServiceImpl(
    private val contactPersonRepository: ContactPersonRepository,
): ContactPersonService {

    @Transactional
    override fun getByCompanyDataId(companyDataId: Long): ContactPerson {
        return contactPersonRepository.findByCompanyDataId(companyDataId)
            ?: throw ContactPersonNotFoundException()
    }


}