package ru.kaplaan.consumer.web.controller.vacancy

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.consumer.domain.entity.vacancy.VacancyResponse
import ru.kaplaan.consumer.service.vacancy.VacancyResponseService
import ru.kaplaan.consumer.web.dto.vacancy.VacancyResponseDto
import ru.kaplaan.consumer.web.mapper.vacancy.toDto
import ru.kaplaan.consumer.web.mapper.vacancy.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate


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

    @PutMapping
    fun update(
        @RequestBody @Validated(OnUpdate::class)
        vacancyResponseDto: VacancyResponseDto
    ) : VacancyResponseDto {
        return vacancyResponseService.update(vacancyResponseDto.toEntity()).toDto()
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

    @GetMapping("/get-vacancy-response/{companyId}/{vacancyId}/{userId}")
    fun getVacancyResponseByUserIdAndVacancyId(
        @PathVariable companyId: Long,
        @PathVariable vacancyId: Long,
        @PathVariable userId: Long
    ): VacancyResponseDto = vacancyResponseService.getVacancyResponseById(companyId, VacancyResponse.PK(userId, vacancyId)).toDto()


    @GetMapping("/get-all-user-id/{companyId}/{vacancyId}/{pageNumber}")
    fun getAllUserIdByCompanyId(
        @PathVariable companyId: Long,
        @PathVariable vacancyId: Long,
        @PathVariable pageNumber: Int
    ): List<Long> {
        return vacancyResponseService.getAllUserIdByVacancyIdAndCompanyId(vacancyId, companyId, pageNumber)
    }
}