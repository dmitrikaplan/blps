package ru.kaplaan.consumer.repository

import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse

@Repository
interface VacancyResponseRepository: CrudRepository<VacancyResponse, VacancyResponse.PK> {


    @Modifying
    @Query("insert into vacancy_response(vacancy_id, user_id) values(:#{#vacancyResponse.pk.vacancyId}, :#{#vacancyResponse.pk.userId})")
    fun saveVacancyResponse(vacancyResponse: VacancyResponse)


    @Modifying
    @Query("delete from vacancy_response where vacancy_id = :#{#id.vacancyId} and user_id = :#{#id.userId}")
    override fun deleteById(id: VacancyResponse.PK)


    @Query("select user_id from vacancy_response where vacancy_id = :vacancyId")
    fun findAllUserIdByVacancyId(vacancyId: Long, pageable: Pageable): List<Long>
}