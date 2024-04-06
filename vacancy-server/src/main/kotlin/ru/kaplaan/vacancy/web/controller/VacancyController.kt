package ru.kaplaan.vacancy.web.controller

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

@RestController
@RequestMapping("/vacancy")
class VacancyController(
    private val vacancyService: VacancyService
) {

    @PostMapping
    fun save(@RequestBody vacancyDto: VacancyDto): VacancyDto =
        vacancyService.save(vacancyDto.toEntity()).toDto()

    @PutMapping
    fun update(@RequestBody vacancyDto: VacancyDto): VacancyDto =
        vacancyService.update(vacancyDto.toEntity()).toDto()

    @DeleteMapping("/{vacancyId}")
    fun delete(@PathVariable vacancyId: Long) =
        vacancyService.delete(vacancyId)

    @GetMapping("/{vacancyId}")
    fun getVacancyById(@PathVariable vacancyId: Long): VacancyDto =
        vacancyService.getVacancyById(vacancyId).toDto()

    @GetMapping("/{companyId}/{page}")
    fun getVacanciesByCompanyId(
        @PathVariable companyId: Long,
        @PathVariable page: Int
    ): List<VacancyDto> =
        vacancyService.getVacanciesByCompanyId(companyId, page).toDto()
}