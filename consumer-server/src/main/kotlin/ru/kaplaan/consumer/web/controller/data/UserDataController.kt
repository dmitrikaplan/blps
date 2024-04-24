package ru.kaplaan.consumer.web.controller.data

import jakarta.validation.constraints.Min
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.consumer.service.data.UserDataService
import ru.kaplaan.consumer.web.dto.data.UserDataDto
import ru.kaplaan.consumer.web.mapper.data.toDto
import ru.kaplaan.consumer.web.mapper.data.toEntity
import ru.kaplaan.consumer.web.validation.OnCreate
import ru.kaplaan.consumer.web.validation.OnUpdate

@RestController
@RequestMapping("/consumer/data/user")
class UserDataController(
    private val userDataService: UserDataService
) {

    @PostMapping
    fun saveUserData(
        @RequestBody @Validated(OnCreate::class)
        userDataDto: UserDataDto
    ): UserDataDto = userDataService.saveUserData(userDataDto.toEntity()).toDto()

    @PutMapping
    fun updateUserData(
        @RequestBody @Validated(OnUpdate::class)
        userDataDto: UserDataDto
    ): UserDataDto = userDataService.updateUserData(userDataDto.toEntity()).toDto()

    @GetMapping("/{userId}")
    fun getUserDataByUserId(
        @PathVariable @Validated @Min(0)
        userId: Long
    ): UserDataDto = userDataService.getUserDataByUserId(userId).toDto()
}