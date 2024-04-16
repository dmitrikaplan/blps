package ru.kaplaan.api.web.controller.consumerServer

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.VacancyService
import ru.kaplaan.api.web.dto.consumerServer.vacancy.ArchiveVacancyDto
import ru.kaplaan.api.web.dto.consumerServer.vacancy.VacancyDto
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate
import java.security.Principal

@RestController
@RequestMapping("/api/v1/vacancy")
class VacancyController(
    private val vacancyService: VacancyService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    fun save(
        @RequestBody @Validated(OnCreate::class) vacancyDto: Mono<VacancyDto>,
        principal: Principal
    ): Mono<ResponseEntity<VacancyDto>> =
        vacancyService.save(
            vacancyDto.map {
                it.apply {
                    companyName = principal.name
                }
            }
        )

    @PutMapping
    @PreAuthorize("hasRole('COMPANY')")
    fun update(
        @RequestBody @Validated(OnUpdate::class) vacancyDto: Mono<VacancyDto>,
        principal: Principal
    ): Mono<ResponseEntity<VacancyDto>> =
        vacancyService.update(
            vacancyDto.map {
                it.apply {
                    companyName = principal.name
                }
            }
        )

    @DeleteMapping("/{vacancyId}")
    @PreAuthorize("hasRole('COMPANY')")
    fun delete(
        @PathVariable vacancyId: Long,
        principal: Principal

    ) = vacancyService.delete(principal.name, vacancyId).also {
            log.debug(principal.name)
        }

    @GetMapping("/{vacancyId}")
    fun getVacancyById(@PathVariable vacancyId: Long): Mono<ResponseEntity<VacancyDto>> =
        vacancyService.getVacancyById(vacancyId)

    @GetMapping("/{companyName}/{page}")
    fun getVacanciesByCompanyName(
        @PathVariable companyName: String,
        @PathVariable page: Int
    ): Mono<ResponseEntity<Flux<VacancyDto>>> =
        vacancyService.getVacanciesByCompanyName(companyName, page)


    @PostMapping("/archive")
    @PreAuthorize("hasRole('COMPANY')")
    fun archiveVacancy(
        @RequestBody @Validated(OnCreate::class)
        archiveVacancyDto: Mono<ArchiveVacancyDto>,
        principal: Principal
    ): Mono<ResponseEntity<Any>> =
        vacancyService.archiveVacancy(
            archiveVacancyDto.map {
                it.apply {
                    this.companyName = principal.name
                }
            }
        )
}