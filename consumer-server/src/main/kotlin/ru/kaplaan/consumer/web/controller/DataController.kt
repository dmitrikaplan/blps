package ru.kaplaan.consumer.web.controller

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.consumer.service.DetailsService
import ru.kaplaan.consumer.web.dto.data.CompanyDataDto
import ru.kaplaan.consumer.web.dto.data.UserDataDto
import ru.kaplaan.consumer.web.mapper.data.toDto
import ru.kaplaan.consumer.web.mapper.data.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

@RestController
@RequestMapping("/consumer/data")
class DataController(
    private val detailsService: DetailsService
) {

    @PostMapping("/company")
    fun saveCompanyData(
        @RequestBody @Validated(OnCreate::class)
        companyDataDto: CompanyDataDto
    ): CompanyDataDto = detailsService.saveCompanyData(companyDataDto.toEntity()).toDto()

    @PutMapping("/company")
    fun updateCompanyData(
        @RequestBody @Validated(OnUpdate::class)
        companyDto: CompanyDataDto
    ): CompanyDataDto = detailsService.updateCompanyData(companyDto.toEntity()).toDto()

    @GetMapping("/company/{companyId}")
    fun getCompanyDataByCompanyName(
        @PathVariable @Validated @NotBlank
        companyId: Long
    ): CompanyDataDto = detailsService.getCompanyDataByCompanyId(companyId).toDto()

    @PostMapping("/user")
    fun saveUserData(
        @RequestBody @Validated(OnCreate::class)
        userDataDto: UserDataDto
    ): UserDataDto = detailsService.saveUserData(userDataDto.toEntity()).toDto()

    @PutMapping("/user")
    fun updateUserData(
        @RequestBody @Validated(OnUpdate::class)
        userDataDto: UserDataDto
    ): UserDataDto = detailsService.updateUserData(userDataDto.toEntity()).toDto()

    @GetMapping("/user/{userId}")
    fun getUserDataByUsername(
        @PathVariable @Validated @Min(0)
        userId: Long
    ): UserDataDto = detailsService.getUserDataByUserId(userId).toDto()

}