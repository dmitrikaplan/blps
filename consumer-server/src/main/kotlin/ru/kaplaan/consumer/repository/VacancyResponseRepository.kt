package ru.kaplaan.consumer.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.vacancyResponse.VacancyResponse

@Repository
interface VacancyResponseRepository: CrudRepository<VacancyResponse, VacancyResponse.PK> {

    @Query("select username from vacancy_response where vacancy_id = :vacancyId")
    fun findAllUsernameByVacancyId(vacancyId: Long, pageable: Pageable): List<String>
}