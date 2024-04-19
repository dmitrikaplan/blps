package ru.kaplaan.consumer.web.controller

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.consumer.service.VacancyService
import ru.kaplaan.consumer.web.dto.vacancy.ArchiveVacancyDto
import ru.kaplaan.consumer.web.dto.vacancy.VacancyDto
import ru.kaplaan.consumer.web.mapper.vacancy.toDto
import ru.kaplaan.consumer.web.mapper.vacancy.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate
import java.security.Principal

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
        @Validated @NotBlank(message = "Название компании не должно быть пустым!")
        @PathVariable companyName: String,
        @Validated @Min(0, message = "Минимальное ID вакансии 0!")
        @PathVariable vacancyId: Long
    ) = vacancyService.delete(companyName, vacancyId)

    @GetMapping("/{vacancyId}")
    fun getVacancyById(@PathVariable vacancyId: Long): VacancyDto =
        vacancyService.getVacancyById(vacancyId).toDto()

    @GetMapping("/{companyName}/{page}")
    fun getVacanciesByCompanyName(
        @Validated @NotBlank(message = "Название компании не должно быть пустым!")
        @PathVariable companyName: String,
        @PathVariable page: Int
    ): List<VacancyDto> = vacancyService.getVacanciesByCompanyName(companyName, page).toDto()

    @GetMapping("/{page}")
    fun getVacancies(
        @PathVariable page: Int
    ): List<VacancyDto> = vacancyService.getVacancies(page).toDto()

    @GetMapping("/{text}/{page}")
    fun getVacanciesByText(
        @PathVariable text: String,
        @PathVariable page: Int
    ): List<VacancyDto> = vacancyService.getVacanciesByText(text, page).toDto()

    @PostMapping("/archive")
    fun archiveVacancy(
        @RequestBody @Validated(OnCreate::class)
        archiveVacancyDto: ArchiveVacancyDto
    ): Unit =
        archiveVacancyDto.let {
            vacancyService.archiveVacancy(it.companyName, it.vacancyId)
        }

    @PostMapping("/unarchive")
    fun unarchiveVacancy(
        @RequestBody @Validated(OnUpdate::class)
        archiveVacancyDto: ArchiveVacancyDto
    ): Unit =
        archiveVacancyDto.let {
            vacancyService.unarchiveVacancy(it.companyName, it.vacancyId)
        }
}