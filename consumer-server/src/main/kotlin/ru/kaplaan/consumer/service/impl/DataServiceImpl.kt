package ru.kaplaan.consumer.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.consumer.domain.entity.data.CompanyData
import ru.kaplaan.consumer.domain.entity.data.UserData
import ru.kaplaan.consumer.domain.exception.alreadyExists.CompanyDataAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.alreadyExists.UserDataAlreadyExistsException
import ru.kaplaan.consumer.domain.exception.notFound.CompanyDataNotFoundException
import ru.kaplaan.consumer.domain.exception.notFound.UserDetailsNotFoundException
import ru.kaplaan.consumer.repository.CompanyDataRepository
import ru.kaplaan.consumer.repository.ContactPersonRepository
import ru.kaplaan.consumer.repository.UserDataRepository
import ru.kaplaan.consumer.service.DetailsService

@Service
class DataServiceImpl(
    private val companyDataRepository: CompanyDataRepository,
    private val userDataRepository: UserDataRepository,
    private val contactPersonRepository: ContactPersonRepository
): DetailsService {

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
            } ?: throw CompanyDataNotFoundException()



    override fun saveUserData(userData: UserData): UserData {
        return userDataRepository.findUserDataByUserId(userData.userId!!)?.let {
            throw UserDataAlreadyExistsException()
        } ?: userDataRepository.save(userData)
    }

    override fun updateUserData(userData: UserData): UserData =
            userDataRepository.save(
                userData.apply {
                    this.id = userDataRepository.findUserDataIdByUserId(userId!!)
                }
            )


    override fun getUserDataByUserId(userId: Long): UserData =
            userDataRepository.findUserDataByUserId(userId)
                ?: throw UserDetailsNotFoundException()

}