package ru.kaplaan.vacancy.web.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.vacancy.service.VacancyService
import ru.kaplaan.vacancy.web.dto.vacancy.VacancyDto

@RestController
@RequestMapping("/vacancy")
class VacancyController(
    private val vacancyService: VacancyService
) {


    @PostMapping
    fun save(@RequestBody vacancyDto: VacancyDto){
        TODO()
    }

    @GetMapping("/{vacancyId}")
    fun getVacancy(@PathVariable vacancyId: Long): VacancyDto{
        TODO()
    }

    @GetMapping("/{companyId}/{page}")
    fun getVacanciesByCompanyId(
        @PathVariable companyId: Long,
        @PathVariable page: Int
    ): List<VacancyDto>{
        TODO()
    }

    @GetMapping
    fun update(@RequestBody vacancyDto: VacancyDto): VacancyDto{
        TODO()
    }

    @DeleteMapping("/{vacancyId}")
    fun delete(@PathVariable vacancyId: Long){
        TODO()
    }
}