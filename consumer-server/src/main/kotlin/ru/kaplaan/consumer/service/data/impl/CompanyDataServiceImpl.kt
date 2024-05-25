package ru.kaplaan.consumer.service.data.impl

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.exception.alreadyExists.CompanyDataAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.CompanyDataNotFoundException
import ru.kaplaan.consumer.repository.consumer.data.CompanyDataRepository
import ru.kaplaan.consumer.service.data.CompanyDataService
import ru.kaplaan.consumer.service.data.ContactPersonService

@Service
class CompanyDataServiceImpl(
    private val companyDataRepository: CompanyDataRepository,
    private val contactPersonService: ContactPersonService
): CompanyDataService {

    @Transactional
    override fun saveCompanyData(companyData: CompanyData): CompanyData {
        return companyDataRepository.findCompanyDataByCompanyId(companyData.companyId!!)?.let {
            throw CompanyDataAlreadyExistsException()
        } ?: companyDataRepository.save(companyData)
    }

    @Transactional
    override fun updateCompanyData(companyData: CompanyData): CompanyData =
        companyDataRepository.save(
            companyData.apply {
                id = companyDataRepository.findCompanyDataIdByCompanyId(companyId!!)
                contactPerson.id = contactPersonService.getByCompanyDataId(this.id!!).id
            }
        )

    @Transactional
    override fun getCompanyDataByCompanyId(companyId: Long): CompanyData =
            companyDataRepository.findCompanyDataByCompanyId(companyId)?.apply {
                this.contactPerson = contactPersonService.getByCompanyDataId(this.id!!)
            } ?: throw CompanyDataNotFoundException()

    @Transactional
    override fun getCompanyEmailByCompanyId(companyId: Long): String {
        return getCompanyDataByCompanyId(companyId).contactPerson.email
    }
}