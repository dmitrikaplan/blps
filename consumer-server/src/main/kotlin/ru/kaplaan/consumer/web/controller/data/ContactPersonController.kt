package ru.kaplaan.consumer.web.controller.data

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kaplaan.consumer.service.data.ContactPersonService
import ru.kaplaan.consumer.web.dto.data.ContactPersonDto
import ru.kaplaan.consumer.web.mapper.data.toDto

@RestController
@RequestMapping("/consumer/data/company/contact-person")
class ContactPersonController(
    private val contactPersonService: ContactPersonService
) {

    @GetMapping("/{companyDataId}")
    fun getContactPersonDto(
        @Validated @Min(0, message = "Id компании не должно быть меньше 0!")
        @PathVariable companyDataId: Long,
    ): ContactPersonDto {
        return contactPersonService.getByCompanyDataId(companyDataId).toDto()
    }

}