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
import java.security.Principal

@RestController
@RequestMapping("/api/v1/vacancy-response")
class VacancyResponseController(
    private val vacancyResponseService: VacancyResponseService
) {

    @PostMapping
    @PreAuthorize("hasRole(USER)")
    fun save(
        @RequestBody @Validated(OnCreate::class)
        vacancyResponseDto: Mono<VacancyResponseDto>,
        principal: Principal
    ): Mono<ResponseEntity<VacancyResponseDto>> =
        vacancyResponseService.save(
            vacancyResponseDto.map {
                it.apply {
                    username = principal.name
                }
            }
        )

    @DeleteMapping("/{vacancyId}")
    fun delete(@PathVariable vacancyId: Long, principal: Principal) =
        vacancyResponseService.delete(vacancyId, principal.name)


    @GetMapping("/{pageNumber}")
    @PreAuthorize("hasRole(COMPANY)")
    fun getAllUserIdByCompanyId(
        @PathVariable pageNumber: Int,
        principal: Principal
    ): Mono<ResponseEntity<Flux<Long>>> =
        vacancyResponseService.getAllUserIdByCompanyName(principal.name, pageNumber)

}