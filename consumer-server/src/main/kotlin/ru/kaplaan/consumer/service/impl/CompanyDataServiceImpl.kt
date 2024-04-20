package ru.kaplaan.consumer.service.impl

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.entity.data.ContactPerson
import ru.kaplaan.consumer.domain.exception.alreadyExists.CompanyDataAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.CompanyDataNotFoundException
import ru.kaplaan.consumer.domain.exception.notFound.ContactPersonNotFoundException
import ru.kaplaan.consumer.repository.CompanyDataRepository
import ru.kaplaan.consumer.repository.ContactPersonRepository
import ru.kaplaan.consumer.service.CompanyDataService

@Service
class CompanyDataServiceImpl(
    private val companyDataRepository: CompanyDataRepository,
    private val contactPersonRepository: ContactPersonRepository
): CompanyDataService {

    override fun saveCompanyData(companyData: CompanyData): CompanyData {
        return companyDataRepository.findCompanyDataByCompanyId(companyData.companyId!!)?.let {
            throw CompanyDataAlreadyExistsException()
        } ?: companyDataRepository.save(companyData)
    }

    override fun updateCompanyData(companyData: CompanyData): CompanyData =
        companyDataRepository.save(
            companyData.apply {
                id = companyDataRepository.findCompanyDataIdByCompanyId(companyId!!)
            }
        )

    override fun getCompanyDataByCompanyId(companyId: Long): CompanyData =
            companyDataRepository.findCompanyDataByCompanyId(companyId)?.apply {
                this.contactPerson = contactPersonRepository.findByCompanyDataId(this.id!!)
                    ?: throw ContactPersonNotFoundException()
            } ?: throw CompanyDataNotFoundException()

    override fun getContactPersonByCompanyId(companyId: Long): ContactPerson {
        return companyDataRepository.findCompanyDataIdByCompanyId(companyId)?.let { companyDataId ->
                contactPersonRepository.findByCompanyDataId(companyDataId)
                    ?: throw ContactPersonNotFoundException()
        } ?: throw CompanyDataNotFoundException()
    }
}