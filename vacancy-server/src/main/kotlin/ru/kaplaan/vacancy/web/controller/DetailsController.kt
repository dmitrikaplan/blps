package ru.kaplaan.vacancy.web.controller

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.vacancy.service.DetailsService
import ru.kaplaan.vacancy.web.dto.details.CompanyDetailsDto
import ru.kaplaan.vacancy.web.dto.details.UserDetailsDto
import ru.kaplaan.vacancy.web.mapper.details.toDto
import ru.kaplaan.vacancy.web.mapper.details.toEntity

@RestController
@RequestMapping("/vacancy/details")
class DetailsController(
    private val detailsService: DetailsService
) {


    @PostMapping("/company")
    fun saveCompanyDetails(
        @RequestBody @Validated
        companyDetailsDto: CompanyDetailsDto
    ): ResponseEntity<String> {
        detailsService.saveCompanyDetails(companyDetailsDto.toEntity())
        return ResponseEntity.ok().body("Информация о компании сохранена")
    }

    @GetMapping("/company/{companyName}")
    fun getCompanyDetailsByCompanyName(
        @Validated @NotBlank(message = "Название компании не должно быть пустым!")
        @PathVariable companyName: String
    ): CompanyDetailsDto =
        detailsService.getCompanyDetailsByCompanyName(companyName).toDto()


    @PutMapping()
    fun updateCompanyDetails(): CompanyDetailsDto{
        TODO()
    }

    @PostMapping("/user")
    fun saveUserDetails(
        @RequestBody
        userDetailsDto: UserDetailsDto
    ): ResponseEntity<String> {
        detailsService.saveUserDetails(userDetailsDto.toEntity())
        return ResponseEntity.ok().body("Информация о пользователе сохранена")
    }

    @GetMapping("/user/{username}")
    fun getUserDetailsByUsername(
        @Validated @Length(min = 4, message = "Название компании должно быть минимум из 4 символов!")
        @PathVariable username: String
    ): UserDetailsDto =
        detailsService.getUserDetailsByUsername(username).toDto()


    @PutMapping()
    fun updateUserDetails(): UserDetailsDto{
        TODO()
    }
}