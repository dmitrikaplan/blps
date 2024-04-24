package ru.kaplaan.consumer.web.controller.data

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.consumer.service.data.CompanyDataService
import ru.kaplaan.consumer.web.dto.data.CompanyDataDto
import ru.kaplaan.consumer.web.mapper.data.toDto
import ru.kaplaan.consumer.web.mapper.data.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

@RestController
@RequestMapping("/consumer/data/company")
class CompanyDataController(
    private val companyDataService: CompanyDataService
) {

    @PostMapping
    fun saveCompanyData(
        @RequestBody @Validated(OnCreate::class)
        companyDataDto: CompanyDataDto
    ): CompanyDataDto = companyDataService.saveCompanyData(companyDataDto.toEntity()).toDto()

    @PutMapping
    fun updateCompanyData(
        @RequestBody @Validated(OnUpdate::class)
        companyDto: CompanyDataDto
    ): CompanyDataDto = companyDataService.updateCompanyData(companyDto.toEntity()).toDto()

    @GetMapping("/{companyId}")
    fun getCompanyDataByCompanyId(
        @Validated @Min(0, message = "Минимальное Id компании - 0!")
        @PathVariable companyId: Long
    ): CompanyDataDto = companyDataService.getCompanyDataByCompanyId(companyId).toDto()


}