package ru.kaplaan.vacancy.service.impl

import org.springframework.stereotype.Service
import ru.kaplaan.vacancy.domain.entity.VacancyResponse
import ru.kaplaan.vacancy.repository.VacancyRepository
import ru.kaplaan.vacancy.repository.VacancyResponseRepository
import ru.kaplaan.vacancy.service.VacancyResponseService

@Service
class VacancyResponseServiceImpl(
    private val vacancyResponseRepository: VacancyResponseRepository,
    private val vacancyRepository: VacancyRepository
): VacancyResponseService {

    override fun save(vacancyResponse: VacancyResponse): VacancyResponse =
        vacancyResponseRepository.save(vacancyResponse)

    override fun delete(vacancyResponseId: Long) =
        vacancyResponseRepository.deleteById(vacancyResponseId)

    override fun getAllUserIdByVacancyId(companyId: Long): List<Long> =
        vacancyRepository.findVacancyIdByCompanyId(companyId).let { vacancyId ->
            vacancyResponseRepository.findAllUserIdByVacancyId(vacancyId)
        }
}