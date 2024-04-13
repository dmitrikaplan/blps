package ru.kaplaan.consumer.web.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.consumer.service.VacancyResponseService
import ru.kaplaan.consumer.web.dto.vacancyResponse.VacancyResponseDto
import ru.kaplaan.consumer.web.mapper.vacancyResponse.toDto
import ru.kaplaan.consumer.web.mapper.vacancyResponse.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate


@RestController
@RequestMapping("/consumer/vacancy-response")
class VacancyResponseController(
    private val vacancyResponseService: VacancyResponseService
) {

    @PostMapping
    fun save(@RequestBody @Validated(OnCreate::class) vacancyResponseDto: VacancyResponseDto): VacancyResponseDto {
        return vacancyResponseService.save(vacancyResponseDto.toEntity()).toDto()
    }

    @DeleteMapping("/{vacancyId}/{username}")
    fun delete(@PathVariable vacancyId: Long, @PathVariable username: String){
        vacancyResponseService.delete(vacancyId, username)
    }


    @GetMapping("/{companyName}/{pageNumber}")
    fun getAllUsernameByCompanyName(
        @PathVariable companyName: String,
        @PathVariable pageNumber: Int
    ): List<String> {
        return vacancyResponseService.getAllUsernamesByCompanyName(companyName, pageNumber)
    }
}