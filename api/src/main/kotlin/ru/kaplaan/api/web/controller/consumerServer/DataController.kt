package ru.kaplaan.api.web.controller.consumerServer

import jakarta.validation.constraints.NotBlank
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.consumerServer.DetailsService
import ru.kaplaan.api.web.dto.consumerServer.details.CompanyDataDto
import ru.kaplaan.api.web.dto.consumerServer.details.UserDataDto
import ru.kaplaan.api.web.validation.OnCreate
import java.security.Principal

@RestController
@RequestMapping("/api/v1/data")
class DataController(
    private val detailsService: DetailsService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping("/company")
    @PreAuthorize("hasRole('COMPANY')")
    fun saveCompanyData(
        @RequestBody @Validated(OnCreate::class)
        companyDataDto: Mono<CompanyDataDto>,
        principal: Principal
    ): Mono<ResponseEntity<String>> {
        return detailsService.saveCompanyData(
            companyDataDto.map {
                log.debug("company name is ${principal.name}")
                it.apply {
                    companyName = principal.name
                }
            }
        )
    }

    @GetMapping("/company/{companyName}")
    fun getCompanyDataByCompanyName(
        @Validated @NotBlank(message = "Название компании не должно быть пустым!")
        @PathVariable companyName: String,
    ): Mono<ResponseEntity<CompanyDataDto>> =
        detailsService.getCompanyDataByCompanyName(companyName)

    @PostMapping("/user")
    @PreAuthorize("hasRole('USER')")
    fun saveUserData(
        @RequestBody @Validated(OnCreate::class)
        userDataDto: Mono<UserDataDto>,
        principal: Principal
    ): Mono<ResponseEntity<String>> =
        detailsService.saveUserData(
            userDataDto.map {
                log.debug("username is ${principal.name}")
                it.apply {
                    username = principal.name
                }
            }
        )

    @GetMapping("/user/{username}")
    fun getUserDataByUsername(
        @Validated @NotBlank(message = "Никнейм пользователя не должен быть пустым!")
        @PathVariable username: String
    ): Mono<ResponseEntity<UserDataDto>> =
        detailsService.getUserDataByUsername(username)
}