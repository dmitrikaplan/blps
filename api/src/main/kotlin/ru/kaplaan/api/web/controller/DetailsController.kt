package ru.kaplaan.api.web.controller

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.DetailsService
import ru.kaplaan.api.web.dto.details.CompanyDetailsDto
import ru.kaplaan.api.web.dto.details.UserDetailsDto

@RestController
@RequestMapping("/details")
class DetailsController(
    private val detailsService: DetailsService
) {


    @PostMapping("/company")
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
        detailsService.getCompanyDetailsByCompanyName(Mono.just(companyName))

    @PostMapping("/user")
    fun saveUserDetails(@RequestBody userDetailsDto: Mono<UserDetailsDto>): Mono<ResponseEntity<String>> =
        detailsService.saveUserDetails(userDetailsDto)

    @GetMapping("/user/{username}")
    fun getUserDetailsByUsername(
        @Validated @Length(min = 4, message = "Название компании должно быть минимум из 4 символов!")
        @PathVariable username: String
    ): Mono<UserDetailsDto> =
        detailsService.getUserDetailsByUsername(Mono.just(username))
}