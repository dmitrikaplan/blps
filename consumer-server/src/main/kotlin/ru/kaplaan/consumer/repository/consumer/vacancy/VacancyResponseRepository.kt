package ru.kaplaan.consumer.repository.consumer.vacancy

import org.springframework.data.domain.Pageable
import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseStatus
import java.time.LocalDate

@Repository
interface VacancyResponseRepository: CrudRepository<VacancyResponse, VacancyResponse.PK> {


    @Modifying
    @Query("""
        insert into vacancy_response(vacancy_id, user_id, status, comment) 
            values(:#{#vacancyResponse.pk.vacancyId}, :#{#vacancyResponse.pk.userId}, :#{#vacancyResponse.status.name}, :#{#vacancyResponse.comment})
    """)
    fun saveVacancyResponse(vacancyResponse: VacancyResponse)

    @Modifying
    @Query("""
        update vacancy_response set status = :#{#vacancyResponse.status.name}, comment = :#{#vacancyResponse.comment}
            where vacancy_id = :#{#vacancyResponse.pk.vacancyId} and user_id = :#{#vacancyResponse.pk.userId}
    """)
    fun updateVacancyResponse(vacancyResponse: VacancyResponse)

    @Modifying
    @Query("delete from vacancy_response where vacancy_id = :#{#id.vacancyId} and user_id = :#{#id.userId}")
    override fun deleteById(id: VacancyResponse.PK)

    @Query("select user_id from vacancy_response where vacancy_id = :vacancyId")
    fun findAllUserIdByVacancyId(vacancyId: Long, pageable: Pageable): List<Long>


    @Query("select exists(select 1 from vacancy_response where vacancy_id = :#{#id.vacancyId} and user_id = :#{#id.userId})")
    override fun existsById(id: VacancyResponse.PK): Boolean

    @Query("select * from vacancy_response where user_id = :userId")
    fun findAllVacancyResponsesByUserId(userId: Long): List<VacancyResponse>

    @Query("select * from vacancy_response where vacancy_id = :#{#id.vacancyId} and user_id = :#{#id.userId}")
    fun findVacancyResponseById(id: VacancyResponse.PK): VacancyResponse?

    @Query("SELECT vacancy_id FROM vacancy_response WHERE status = :status and date_last_status_update BETWEEN :startDate AND :finishDate")
    fun findAllVacancyIdByAcceptedAndDateLastStatusUpdateBetween(startDate: LocalDate, finishDate: LocalDate, status: VacancyResponseStatus): List<Long>

    @Query("select count(*) from vacancy_response where vacancy_id = :vacancyId and status = :status and date_last_status_update between :startDate and :finishDate")
    fun countVacancyResponsesByDateBetweenAndVacancyIdAndAccepted(startDate: LocalDate, finishDate: LocalDate, vacancyId: Long, status: VacancyResponseStatus): Long
}