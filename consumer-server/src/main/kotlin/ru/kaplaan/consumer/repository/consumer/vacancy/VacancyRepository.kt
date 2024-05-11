package ru.kaplaan.consumer.repository.consumer.vacancy

import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.vacancy.Vacancy

@Repository
interface VacancyRepository: CrudRepository<Vacancy, Long> {
    @Query("select * from vacancy where company_id = :companyId and is_archived = false")
    fun findAllByCompanyId(companyId: Long, pageable: Pageable): List<Vacancy>

    @Query("select * from vacancy where is_archived = false")
    fun findAllVacancies(pageable: Pageable): List<Vacancy>

    @Query("select * from vacancy where title ~* '^. *:text .*\$' or description ~* '^. *:text .*\$' where is_archived = false")
    fun findAllByVacanciesByText(text: String, pageable: Pageable): List<Vacancy>

    @Modifying
    @Query("delete from vacancy where company_id = :companyId and vacancy_id = :vacancyId")
    fun deleteByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Query("select exists (select 1 from vacancy where vacancy_id = :vacancyId and company_id = :companyId)")
    fun existVacancyByVacancyIdAndCompanyId(vacancyId: Long, companyId: Long): Boolean

    @Modifying
    @Query("update vacancy set is_archived = true where company_id = :companyId and vacancy_id = :vacancyId")
    fun archiveVacancyByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Modifying
    @Query("update vacancy set is_archived = false where company_id = :companyId and vacancy_id = :vacancyId")
    fun unarchiveVacancyByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Query("select * from vacancy where vacancy_id = :vacancyId")
    fun findVacancyById(vacancyId: Long): Vacancy?
}