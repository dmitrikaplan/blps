package ru.kaplaan.api.web.controller

import jakarta.validation.constraints.NotBlank
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.DetailsService
import ru.kaplaan.api.web.dto.details.CompanyDetailsDto
import ru.kaplaan.api.web.dto.details.UserDetailsDto

@RestController
@RequestMapping("/api/v1/details")
class DetailsController(
    private val detailsService: DetailsService
) {

    @PostMapping("/company")
    @PreAuthorize("hasRole(COMPANY)")
    fun saveCompanyDetails(
        @RequestBody @Validated
        companyDetailsDto: Mono<CompanyDetailsDto>
    ): Mono<ResponseEntity<String>> {
        return detailsService.saveCompanyDetails(companyDetailsDto)
    }

    @GetMapping("/company/{companyName}")
    fun getCompanyDetailsByCompanyName(
        @Validated @NotBlank(message = "Название компании не должно быть пустым!")
        @PathVariable companyName: String
    ): Mono<ResponseEntity<CompanyDetailsDto>> =
        detailsService.getCompanyDetailsByCompanyName(companyName)

    @PostMapping("/user")
    @PreAuthorize("hasRole(USER)")
    fun saveUserDetails(@RequestBody userDetailsDto: Mono<UserDetailsDto>): Mono<ResponseEntity<String>> =
        detailsService.saveUserDetails(userDetailsDto)

    @GetMapping("/user/{username}")
    fun getUserDetailsByUsername(
        @Validated @NotBlank(message = "Никнейм пользователя не должен быть пустым!")
        @PathVariable username: String
    ): Mono<ResponseEntity<UserDetailsDto>> =
        detailsService.getUserDetailsByUsername(username)
}