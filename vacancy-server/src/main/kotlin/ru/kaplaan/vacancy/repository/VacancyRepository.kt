package ru.kaplaan.vacancy.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.vacancy.domain.entity.vacancy.Vacancy

@Repository
interface VacancyRepository: CrudRepository<Vacancy, Long> {
    @Query("select * from vacancy where company_id = :companyId")
    fun findAllByCompanyId(companyId: Long, pageable: Pageable): List<Vacancy>

    @Query("select vacancy_id from vacancy where company_name = :companyName")
    fun findVacancyIdByCompanyName(companyName: String): Long

    @Query("delete from vacancy where company_id = :companyId and vacancy_id = :vacancyId")
    fun deleteByCompanyIdAndVacancyId(companyId: Long, vacancyId: Long)

    @Query("select * from vacancy where vacancy_id = :vacancyId")
    fun findVacancyByVacancyId(vacancyId: Long): Vacancy?
}