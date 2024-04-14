package ru.kaplaan.authserver.web.controller

import org.springframework.web.bind.annotation.*
import ru.kaplaan.authserver.service.UserInfoService

@RestController
@RequestMapping("/user-info")
class UserInfoController(
    private val userInfoService: UserInfoService
) {

    @GetMapping("/user-id/{username}")
    fun getUserIdByUsername(@PathVariable username: String): Long =
        userInfoService.getUserIdByUsername(username)

    @GetMapping("/username/{userId}")
    fun getUsernameByUserId(@PathVariable userId: Long): String =
        userInfoService.getUsernameByUserId(userId)

    @PostMapping("/get-usernames-by-user-ids")
    fun getUsernamesByUserIds(@RequestBody ids: List<Long>): List<String> =
        userInfoService.getUsernamesByUserIds(ids)
}