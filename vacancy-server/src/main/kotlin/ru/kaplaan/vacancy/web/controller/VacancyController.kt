package ru.kaplaan.vacancy.web.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.vacancy.service.VacancyService
import ru.kaplaan.vacancy.web.dto.vacancy.VacancyDto
import ru.kaplaan.vacancy.web.mapper.vacancy.toDto
import ru.kaplaan.vacancy.web.mapper.vacancy.toEntity
import ru.kaplaan.vacancy.web.validation.OnCreate
import ru.kaplaan.vacancy.web.validation.OnUpdate

@RestController
@RequestMapping("/consumer/vacancy")
class VacancyController(
    private val vacancyService: VacancyService
) {

    @PostMapping
    fun save(@RequestBody @Validated(OnCreate::class) vacancyDto: VacancyDto): VacancyDto =
        vacancyService.save(vacancyDto.toEntity()).toDto()

    @PutMapping
    fun update(@RequestBody @Validated(OnUpdate::class) vacancyDto: VacancyDto): VacancyDto =
        vacancyService.update(vacancyDto.toEntity()).toDto()

    @DeleteMapping("{companyName}/{vacancyId}")
    fun delete(
        @PathVariable companyName: String,
        @PathVariable vacancyId: Long
    ) =
        vacancyService.delete(companyName, vacancyId)

    @GetMapping("/{vacancyId}")
    fun getVacancyById(@PathVariable vacancyId: Long): VacancyDto =
        vacancyService.getVacancyById(vacancyId).toDto()

    @GetMapping("/{companyName}/{page}")
    fun getVacanciesByCompanyName(
        @PathVariable companyName: String,
        @PathVariable page: Int
    ): List<VacancyDto> =
        vacancyService.getVacanciesByCompanyName(companyName, page).toDto()
}