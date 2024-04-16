package ru.kaplaan.api.web.controller.consumerServer

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotBlank
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
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

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/company")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Добавление информации о компании")
    fun saveCompanyData(
        @RequestBody @Validated(OnCreate::class)
        companyDataDto: Mono<CompanyDataDto>,
        principal: Principal
    ): Mono<ResponseEntity<String>> {
        return dataService.saveCompanyData(
            companyDataDto.map {
                it.apply {
                    companyName = principal.name
                }
            }
        )
    }


    @PutMapping("/company")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Обновление информации о компании")
    fun updateCompanyData(
        @RequestBody @Validated(OnUpdate::class)
        companyDataDto: Mono<CompanyDataDto>,
        principal: Principal
    ): Mono<ResponseEntity<String>> {
        return dataService.updateCompanyData(
            companyDataDto.map {
                it.apply {
                    companyName = principal.name
                }
            }
        )
    }

    @GetMapping("/company/{companyName}")
    @Operation(description = "Получить информацию о компании по названию компании")
    fun getCompanyDataByCompanyName(
        @Validated @NotBlank(message = "Название компании не должно быть пустым!")
        @PathVariable companyName: String,
    ): Mono<ResponseEntity<CompanyDataDto>> =
        dataService.getCompanyDataByCompanyName(companyName)


    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Сохранить информацию о пользователе")
    fun saveUserData(
        @RequestBody @Validated(OnCreate::class)
        userDataDto: Mono<UserDataDto>,
        principal: Principal
    ): Mono<ResponseEntity<String>> =
        dataService.saveUserData(
            userDataDto.map {
                log.debug("username is ${principal.name}")
                it.apply {
                    username = principal.name
                }
            }
        )

    @PutMapping("/user")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Обновление информации о пользователе")
    fun updateUserData(
        @RequestBody @Validated(OnUpdate::class)
        userDataDto: Mono<UserDataDto>,
        principal: Principal
    ): Mono<ResponseEntity<String>> =
        dataService.updateUserData(
            userDataDto.map {
                it.apply {
                    username = principal.name
                }
            }
        )

    @GetMapping("/user/{username}")
    @Operation(summary = "Получить информацию о пользователе")
    fun getUserDataByUsername(
        @Validated @NotBlank(message = "Никнейм пользователя не должен быть пустым!")
        @Parameter(description = "Username пользователя")
        @PathVariable username: String
    ): Mono<ResponseEntity<UserDataDto>> =
        dataService.getUserDataByUsername(username)
}