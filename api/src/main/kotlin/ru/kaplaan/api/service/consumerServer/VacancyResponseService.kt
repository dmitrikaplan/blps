package ru.kaplaan.api.service.consumerServer

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.consumerServer.vacancyResponse.VacancyResponseDto

@Service
interface VacancyResponseService {
    fun save(vacancyResponseDto: Mono<VacancyResponseDto>): Mono<ResponseEntity<VacancyResponseDto>>

    fun delete(vacancyId: Long, userId: Long): Mono<ResponseEntity<Any>>

    fun getAllUserIdByCompanyId(companyId: Long, vacancyId: Long, pageNumber: Int): Mono<ResponseEntity<Flux<Long>>>
}