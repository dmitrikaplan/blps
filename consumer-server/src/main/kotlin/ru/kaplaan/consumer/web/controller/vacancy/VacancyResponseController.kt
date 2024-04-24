package ru.kaplaan.consumer.web.controller.vacancy

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.consumer.service.VacancyResponseService
import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseDto
import ru.kaplaan.consumer.web.mapper.vacancy.toDto
import ru.kaplaan.consumer.web.mapper.vacancy.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate


@RestController
@RequestMapping("/consumer/vacancy-response")
class VacancyResponseController(
    private val vacancyResponseService: VacancyResponseService
) {

    @PostMapping
    fun save(
        @RequestBody @Validated(OnCreate::class)
        vacancyResponseDto: VacancyResponseDto
    ): VacancyResponseDto {
        return vacancyResponseService.save(vacancyResponseDto.toEntity()).toDto()
    }

    @DeleteMapping("/{vacancyId}/{userId}")
    fun delete(
        @Validated @Min(0, message = "Id вакансии не должен быть меньше 0!")
        @PathVariable vacancyId: Long,
        @Validated @Min(0, message = "Id пользователя не должен быть меньше 0!")
        @PathVariable userId: Long
    ){
        return vacancyResponseService.delete(vacancyId, userId)
    }


    @GetMapping("/{companyId}/{vacancyId}/{pageNumber}")
    fun getAllUsernameByCompanyId(
        @PathVariable companyId: Long,
        @PathVariable vacancyId: Long,
        @PathVariable pageNumber: Int
    ): List<Long> {
        return vacancyResponseService.getAllUserIdByVacancyIdAndCompanyId(vacancyId, companyId, pageNumber)
    }
}