package ru.kaplaan.vacancy.web.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.vacancy.service.VacancyResponseService
import ru.kaplaan.vacancy.web.dto.vacancyResponse.VacancyResponseDto
import ru.kaplaan.vacancy.web.mapper.vacancyResponse.toDto
import ru.kaplaan.vacancy.web.mapper.vacancyResponse.toEntity


@RestController
class VacancyResponseController(
    private val vacancyResponseService: VacancyResponseService
) {

    @PostMapping
    fun save(@RequestBody @Validated vacancyResponseDto: VacancyResponseDto): VacancyResponseDto{
        return vacancyResponseService.save(vacancyResponseDto.toEntity()).toDto()
    }

    @DeleteMapping("/{vacancyResponseId}")
    fun delete(@PathVariable vacancyResponseId: Long){
        vacancyResponseService.delete(vacancyResponseId)
    }


    @GetMapping("/{companyId}")
    fun getAllUserIdByCompanyId(@PathVariable companyId: Long): List<Long> {
        return vacancyResponseService.getAllUserIdByVacancyId(companyId)
    }
}