package ru.kaplaan.vacancy.web.controller

import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.vacancy.service.DetailsService
import ru.kaplaan.vacancy.web.dto.details.CompanyDataDto
import ru.kaplaan.vacancy.web.dto.details.UserDataDto
import ru.kaplaan.vacancy.web.mapper.details.toDto
import ru.kaplaan.vacancy.web.mapper.details.toEntity
import ru.kaplaan.vacancy.web.validation.OnCreate
import ru.kaplaan.vacancy.web.validation.OnUpdate

@RestController
@RequestMapping("/vacancy/details")
class DetailsController(
    private val detailsService: DetailsService
) {

    @PostMapping("/company")
    fun saveCompanyData(
        @RequestBody @Validated(OnCreate::class)
        companyDataDto: CompanyDataDto
    ): ResponseEntity<String> {
        detailsService.saveCompanyData(companyDataDto.toEntity())
        return ResponseEntity.ok().body("Информация о компании сохранена")
    }

    @PutMapping("/company")
    fun updateCompanyData(
        @RequestBody @Validated(OnUpdate::class) companyDto: CompanyDataDto
    ): CompanyDataDto =
        detailsService.updateCompanyData(companyDto.toEntity()).toDto()

    @GetMapping("/company/{companyName}")
    fun getCompanyDataByCompanyName(
        @Validated @NotBlank(message = "Название компании не должно быть пустым!")
        @PathVariable companyName: String
    ): CompanyDataDto =
        detailsService.getCompanyDataByCompanyName(companyName).toDto()

    @PostMapping("/user")
    fun saveUserData(
        @RequestBody
        userDataDto: UserDataDto
    ): ResponseEntity<String> {
        detailsService.saveUserData(userDataDto.toEntity())
        return ResponseEntity.ok().body("Информация о пользователе сохранена")
    }

    @PutMapping("/user")
    fun updateUserData(
        @RequestBody @Validated(OnUpdate::class)
        userDataDto: UserDataDto
    ): UserDataDto =
        detailsService.updateUserData(userDataDto.toEntity()).toDto()

    @GetMapping("/user/{username}")
    fun getUserDataByUsername(
        @Validated @Length(min = 4, message = "Название компании должно быть минимум из 4 символов!")
        @PathVariable username: String
    ): UserDataDto =
        detailsService.getUserDataByUsername(username).toDto()

}