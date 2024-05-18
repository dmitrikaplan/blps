package ru.kaplaan.consumer.domain.entity.vacancy

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Embedded
import org.springframework.data.relational.core.mapping.Table
import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseStatus
import java.time.LocalDate

@Table("vacancy_response")
class VacancyResponse{

    @Id
    @Embedded(onEmpty = Embedded.OnEmpty.USE_EMPTY)
    lateinit var pk: PK

    lateinit var comment: String

    @Column("status")
    lateinit var status: VacancyResponseStatus

    @Column("date_last_status_update")
    var dateLastStatusUpdate: LocalDate = LocalDate.now()

    data class PK(
        @Column("vacancy_id")
        val vacancyId: Long,
        @Column("user_id")
        val userId: Long
    )
}