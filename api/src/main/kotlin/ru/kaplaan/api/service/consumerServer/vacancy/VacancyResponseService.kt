package ru.kaplaan.api.service.consumerServer.vacancy

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.consumerServer.vacancy.VacancyResponseDto

@Service
interface VacancyResponseService {
    fun save(vacancyResponseDto: Mono<VacancyResponseDto>): Mono<VacancyResponseDto>

    fun delete(vacancyId: Long, userId: Long): Mono<Any>

    fun getAllUserIdByCompanyId(companyId: Long, vacancyId: Long, pageNumber: Int): Flux<Long>
}