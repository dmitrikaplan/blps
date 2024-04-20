package ru.kaplaan.api.service.consumerServer

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.consumerServer.vacancy.ArchiveVacancyDto
import ru.kaplaan.api.web.dto.consumerServer.vacancy.VacancyDto


@Service
interface VacancyService {

    fun save(vacancyDto: Mono<VacancyDto>): Mono<ResponseEntity<VacancyDto>>

    fun update(vacancyDto: Mono<VacancyDto>): Mono<ResponseEntity<VacancyDto>>

    fun delete(companyId: Long, vacancyId: Long): Mono<ResponseEntity<Any>>

    fun getVacancyById(vacancyId: Long): Mono<ResponseEntity<VacancyDto>>

    fun getVacanciesByCompanyId(companyId: Long, pageNumber: Int): Mono<ResponseEntity<Flux<VacancyDto>>>

    fun getVacancies(pageNumber: Int): Mono<ResponseEntity<Flux<VacancyDto>>>

    fun getVacanciesByText(text: String, pageNumber: Int): Mono<ResponseEntity<Flux<VacancyDto>>>

    fun archiveVacancy(archiveVacancyDto: Mono<ArchiveVacancyDto>): Mono<ResponseEntity<Any>>

    fun unarchiveVacancy(archiveVacancyDto: Mono<ArchiveVacancyDto>): Mono<ResponseEntity<Any>>
}