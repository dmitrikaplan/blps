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

    @PutMapping("/{companyId}")
    fun update(
        @RequestBody @Validated(OnUpdate::class)
        vacancyResponseDto: VacancyResponseDto,
        @Validated @Min(0, message = "Минимальное Id компании - 0!")
        @PathVariable companyId: Long
    ) : VacancyResponseDto {
        return vacancyResponseService.update(vacancyResponseDto.toEntity(), companyId).toDto()
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

    @GetMapping("/get-all-vacancy-responses/{userId}")
    fun getAllVacancyResponsesByUserId(
        @Validated @Min(0, message = "Минимальное Id пользователя - 0!")
        @PathVariable userId: Long
    ): List<VacancyResponseDto> = vacancyResponseService.getAllVacancyResponsesByUserId(userId).toDto()

    @GetMapping("/get-vacancy-response/{vacancyId}/{userId}")
    fun getVacancyResponseById(
        @Validated @Min(0, message = "Минимальное Id вакансии 0!")
        @PathVariable vacancyId: Long,
        @Validated @Min(0, message = "Минимальное Id пользователя 0!")
        @PathVariable userId: Long
    ): VacancyResponseDto = vacancyResponseService.getVacancyResponseById(VacancyResponse.PK(vacancyId, userId)).toDto()

    @GetMapping("/get-vacancy-response/{companyId}/{vacancyId}/{userId}")
    fun getVacancyResponseByCompanyIdAndId(
        @Validated @Min(0, message = "Минимальное Id компании 0!")
        @PathVariable companyId: Long,
        @Validated @Min(0, message = "Минимальное Id вакансии 0!")
        @PathVariable vacancyId: Long,
        @Validated @Min(0, message = "Минимальное Id пользователя 0!")
        @PathVariable userId: Long
    ): VacancyResponseDto = vacancyResponseService.getVacancyResponseByIdAndCompanyId(companyId, VacancyResponse.PK(vacancyId, userId)).toDto()


    @GetMapping("/get-all-user-id/{companyId}/{vacancyId}/{pageNumber}")
    fun getAllUserIdByCompanyId(
        @PathVariable companyId: Long,
        @PathVariable vacancyId: Long,
        @PathVariable pageNumber: Int
    ): List<Long> {
        return vacancyResponseService.getAllUserIdByVacancyIdAndCompanyId(vacancyId, companyId, pageNumber)
    }
}