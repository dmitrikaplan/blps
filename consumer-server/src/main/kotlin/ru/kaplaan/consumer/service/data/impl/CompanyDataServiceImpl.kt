package ru.kaplaan.consumer.service.data.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.exception.alreadyExists.CompanyDataAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.CompanyDataNotFoundException
import ru.kaplaan.consumer.repository.CompanyDataRepository
import ru.kaplaan.consumer.service.data.CompanyDataService
import ru.kaplaan.consumer.service.data.ContactPersonService

@Service
class CompanyDataServiceImpl(
    private val companyDataRepository: CompanyDataRepository,
    private val contactPersonService: ContactPersonService
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
                contactPerson.id = contactPersonService.getByCompanyDataId(this.id!!).id
            }
        )

    override fun getCompanyDataByCompanyId(companyId: Long): CompanyData =
            companyDataRepository.findCompanyDataByCompanyId(companyId)?.apply {
                this.contactPerson = contactPersonService.getByCompanyDataId(this.id!!)
            } ?: throw CompanyDataNotFoundException()
}