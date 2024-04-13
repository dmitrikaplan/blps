package ru.kaplaan.vacancy.web.controller

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.vacancy.service.DetailsService
import ru.kaplaan.vacancy.web.dto.data.CompanyDataDto
import ru.kaplaan.vacancy.web.dto.data.UserDataDto
import ru.kaplaan.vacancy.web.mapper.data.toDto
import ru.kaplaan.vacancy.web.mapper.data.toEntity
import ru.kaplaan.vacancy.web.validation.OnCreate
import ru.kaplaan.vacancy.web.validation.OnUpdate

@RestController
@RequestMapping("/consumer/details")
class DataController(
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
        @RequestBody @Validated(OnUpdate::class)
        companyDto: CompanyDataDto
    ): CompanyDataDto =
        detailsService.updateCompanyData(companyDto.toEntity()).toDto()

    @GetMapping("/company/{companyName}")
    fun getCompanyDataByCompanyName(@PathVariable companyName: String): CompanyDataDto =
        detailsService.getCompanyDataByCompanyName(companyName).toDto()

    @PostMapping("/user")
    fun saveUserData(@RequestBody userDataDto: UserDataDto): ResponseEntity<String> {
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
    fun getUserDataByUsername(@PathVariable username: String): UserDataDto =
        detailsService.getUserDataByUsername(username).toDto()

}