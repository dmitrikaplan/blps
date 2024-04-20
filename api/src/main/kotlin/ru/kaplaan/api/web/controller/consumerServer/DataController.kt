package ru.kaplaan.api.web.controller.consumerServer

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.DataService
import ru.kaplaan.api.web.dto.consumerServer.data.CompanyDataDto
import ru.kaplaan.api.web.dto.consumerServer.data.UserDataDto
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnUpdate
import java.security.Principal

@RestController
@RequestMapping("/api/v1/data")
@Tag(name = "Data controller", description = "Контролер взаимодействия с данными пользователей")
class DataController(
    private val dataService: DataService
) {

    @PostMapping("/company")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Добавление информации о компании")
    fun saveCompanyData(
        @RequestBody @Validated(OnCreate::class)
        companyDataDto: Mono<CompanyDataDto>,
        authentication: Authentication
    ): Mono<ResponseEntity<CompanyDataDto>> =
        dataService.saveCompanyData(
            companyDataDto.map {
                it.apply {
                    companyId = authentication.details as Long
                }
            }
        )


    @PutMapping("/company")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Обновление информации о компании")
    fun updateCompanyData(
        @RequestBody @Validated(OnUpdate::class)
        companyDataDto: Mono<CompanyDataDto>,
        authentication: Authentication
    ): Mono<ResponseEntity<CompanyDataDto>> =
        dataService.updateCompanyData(
            companyDataDto.map {
                it.apply {
                    companyId = authentication.details as Long
                }
            }
        )

    @GetMapping("/company/{companyId}")
    @Operation(summary = "Получить информацию о компании по названию компании")
    fun getCompanyDataByCompanyId(
        @Validated @Min(0, message = "Id компании не должен быть больше или равен 0!")
        @Parameter(description = "название компании", required = true)
        @PathVariable companyId: Long
    ): Mono<ResponseEntity<CompanyDataDto>> = dataService.getCompanyDataByCompanyId(companyId)


    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Сохранить информацию о пользователе")
    fun saveUserData(
        @RequestBody @Validated(OnCreate::class)
        userDataDto: Mono<UserDataDto>,
        authentication: Authentication
    ): Mono<ResponseEntity<UserDataDto>> =
        dataService.saveUserData(
            userDataDto.map {
                it.apply {
                    userId = authentication.details as Long
                }
            }
        )

    @PutMapping("/user")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Обновление информации о пользователе")
    fun updateUserData(
        @RequestBody @Validated(OnUpdate::class)
        userDataDto: Mono<UserDataDto>,
        authentication: Authentication
    ): Mono<ResponseEntity<UserDataDto>> =
        dataService.updateUserData(
            userDataDto.map {
                it.apply {
                    userId = authentication.details as Long
                }
            }
        )

    @GetMapping("/user/{userId}")
    @Operation(summary = "Получить информацию о пользователе")
    fun getUserDataByUsername(
        @Validated @Min(0, message = "Id пользователя должен быть больше или равен 0!")
        @Parameter(description = "Id пользователя")
        @PathVariable userId: Long
    ): Mono<ResponseEntity<UserDataDto>> = dataService.getUserDataByUserId(userId)
}