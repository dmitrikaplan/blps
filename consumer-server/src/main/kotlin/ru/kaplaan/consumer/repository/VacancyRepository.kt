package ru.kaplaan.consumer.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy

@Repository
interface VacancyRepository: CrudRepository<Vacancy, Long> {
    @Query("select * from vacancy where company_id = :companyId and is_archived = :isArchived")
    fun findAllByCompanyId(companyId: Long, pageable: Pageable, isArchived: Boolean = false): List<Vacancy>

    @Query("select vacancy_id from vacancy where company_id = :companyId and is_archived = :isArchived")
    fun findAllVacancyIdByCompanyId(companyId: Long, isArchived: Boolean): List<Long>


    @Query("select * from vacancy where is_archived = :isArchived")
    fun findAllVacancies(pageable: Pageable, isArchived: Boolean = false): List<Vacancy>

    @Query("select * from vacancy where title ~* '^. *:text .*\$' or description ~* '^. *:text .*\$' where is_archived = :isArchived")
    fun findAllByVacanciesByText(text: String, pageable: Pageable, isArchived: Boolean = false): List<Vacancy>

    @Query("delete from vacancy where company_id = :companyId and vacancy_id = :vacancyId")
    fun deleteByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Query("select * from vacancy where vacancy_id = :vacancyId and is_archived = :isArchived")
    fun findVacancyByVacancyId(vacancyId: Long, isArchived: Boolean = false): Vacancy?

    @Modifying
    @Query("update vacancy set is_archived = true where company_id = :companyId and vacancy_id = :vacancyId")
    fun archiveVacancyByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Modifying
    @Query("update vacancy set is_archived = false where company_id = :companyId and vacancy_id = :vacancyId")
    fun unarchiveVacancyByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Query("select * from vacancy where vacancy_id = :vacancyId")
    fun findVacancyById(vacancyId: Long): Vacancy?
}