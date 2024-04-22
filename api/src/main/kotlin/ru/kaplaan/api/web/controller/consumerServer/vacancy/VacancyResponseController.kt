package ru.kaplaan.api.web.controller.consumerServer.vacancy

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.Min
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.vacancy.VacancyResponseService
import ru.kaplaan.api.web.dto.consumerServer.vacancy.VacancyResponseDto
import ru.kaplaan.api.web.validation.OnCreate

@RestController
@RequestMapping("/api/v1/vacancy-response")
@Tag(name = "Vacancy Response Controller", description = "Контролер отклика на вакансию")
class VacancyResponseController(
    private val vacancyResponseService: VacancyResponseService
) {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Откликнуться на вакансию")
    fun save(
        @RequestBody @Validated(OnCreate::class)
        vacancyResponseDto: Mono<VacancyResponseDto>,
        authentication: Authentication
    ): Mono<VacancyResponseDto> =
        vacancyResponseService.save(
            vacancyResponseDto.map {
                it.apply {
                    userId = (authentication.details as String).toLong()
                }
            }
        )

    //TODO: удалять отклик на вакансию должен работодатель!
    @DeleteMapping("/{vacancyId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Удалить отклик на вакансию")
    fun delete(
        @Validated @Min(0)
        @Parameter(description = "Id вакансии", required = true)
        @PathVariable vacancyId: Long,
        authentication: Authentication
    ): Mono<Any> = vacancyResponseService.delete(vacancyId, (authentication.details as String).toLong())


    @GetMapping("/{vacancyId}/{pageNumber}")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Получить все Id пользователей")
    fun getAllUserIdByCompanyName(
        @Parameter(description = "Id вакансии", required = true)
        @PathVariable vacancyId: Long,
        @Parameter(description = "Номер страницы", required = true)
        @Validated @Min(0, message = "Номер страницы должен быть положительным числом!")
        @PathVariable pageNumber: Int,
        authentication: Authentication
    ): Flux<Long> =
        vacancyResponseService.getAllUserIdByCompanyId((authentication.details as String).toLong(), vacancyId, pageNumber)

}