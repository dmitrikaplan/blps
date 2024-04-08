package ru.kaplaan.api.web.controller.consumerServer

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.VacancyResponseService
import ru.kaplaan.api.web.dto.consumerServer.vacancyResponse.VacancyResponseDto
import ru.kaplaan.api.web.validation.OnCreate

@RestController
@RequestMapping("/api/v1/vacancy-response")
class VacancyResponseController(
    private val vacancyResponseService: VacancyResponseService
) {

    @PostMapping
    @PreAuthorize("hasRole(USER)")
    fun save(
        @RequestBody @Validated(OnCreate::class)
        vacancyResponseDto: Mono<VacancyResponseDto>
    ): Mono<ResponseEntity<VacancyResponseDto>> =
        vacancyResponseService.save(vacancyResponseDto)

    @DeleteMapping("/{vacancyResponseId}")
    fun delete(@PathVariable vacancyResponseId: Long) =
        vacancyResponseService.delete(vacancyResponseId)


    @GetMapping("/{companyId}/{pageNumber}")
    @PreAuthorize("hasRole(COMPANY)")
    fun getAllUserIdByCompanyId(
        @PathVariable companyId: Long,
        @PathVariable pageNumber: Int
    ): Mono<ResponseEntity<Flux<Long>>> =
        vacancyResponseService.getAllUserIdByVacancyId(companyId, pageNumber)

}