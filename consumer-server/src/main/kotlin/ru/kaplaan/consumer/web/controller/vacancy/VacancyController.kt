package ru.kaplaan.consumer.web.controller.vacancy

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.consumer.service.vacancy.VacancyService
import ru.kaplaan.consumer.web.dto.vacancy.ArchiveVacancyDto
import ru.kaplaan.consumer.web.dto.vacancy.VacancyDto
import ru.kaplaan.consumer.web.mapper.vacancy.toDto
import ru.kaplaan.consumer.web.mapper.vacancy.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

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

    @DeleteMapping("{companyId}/{vacancyId}")
    fun delete(
        @Validated @Min(0, message = "Минимальное Id компании 0!")
        @PathVariable companyId: Long,
        @Validated @Min(0, message = "Минимальное Id вакансии 0!")
        @PathVariable vacancyId: Long
    ): Unit = vacancyService.delete(companyId, vacancyId)

    @GetMapping("/get-by-vacancy-id/{vacancyId}")
    fun getVacancyById(
        @PathVariable @Validated
        @Min(0) vacancyId: Long
    ): VacancyDto = vacancyService.getVacancyById(vacancyId).toDto()

    @GetMapping("/get-by-company-id/{companyId}/{page}")
    fun getVacanciesByCompanyName(
        @Validated @Min(0, message = "Минимальное Id компании 0!")
        @PathVariable companyId: Long,
        @PathVariable page: Int
    ): List<VacancyDto> = vacancyService.getVacanciesByCompanyId(companyId, page).toDto()

    @GetMapping("/{page}")
    fun getVacancies(
        @PathVariable page: Int
    ): List<VacancyDto> = vacancyService.getVacancies(page).toDto()

    @GetMapping("/get-by-text/{text}/{page}")
    fun getVacanciesByText(
        @PathVariable text: String,
        @PathVariable page: Int
    ): List<VacancyDto> = vacancyService.getVacanciesByText(text, page).toDto()

    @PutMapping("/archive")
    fun archiveVacancy(
        @RequestBody @Validated(OnCreate::class)
        archiveVacancyDto: ArchiveVacancyDto
    ): Unit =
        archiveVacancyDto.let {
            vacancyService.archiveVacancy(it.companyId, it.vacancyId)
        }

    @PutMapping("/unarchive")
    fun unarchiveVacancy(
        @RequestBody @Validated(OnCreate::class)
        archiveVacancyDto: ArchiveVacancyDto
    ): Unit =
        archiveVacancyDto.let {
            vacancyService.unarchiveVacancy(it.companyId, it.vacancyId)
        }
}