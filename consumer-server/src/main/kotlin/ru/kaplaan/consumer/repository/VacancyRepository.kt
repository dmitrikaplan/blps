package ru.kaplaan.consumer.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy

@Repository
interface VacancyRepository: CrudRepository<Vacancy, Long> {
    @Query("select * from vacancy where company_id = :companyId")
    fun findAllByCompanyId(companyId: Long, pageable: Pageable): List<Vacancy>

    @Query("select vacancy_id from vacancy where company_id = :companyId")
    fun findVacancyIdByCompanyId(companyId: Long): Long

    @Query("delete from vacancy where company_id = :companyId and vacancy_id = :vacancyId")
    fun deleteByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Query("select * from vacancy where vacancy_id = :vacancyId and is_archived = false")
    fun findVacancyByVacancyIdAndNotIsArchived(vacancyId: Long): Vacancy?

    @Query("update vacancy set is_archived = true where company_id = :companyId and vacancy_id = :vacancyId")
    fun archiveVacancyByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Query("update vacancy set is_archived = false where company_id = :companyId and vacancy_id = :vacancyId")
    fun unarchiveVacancyByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)
}