package ru.kaplaan.authserver.web.controller

import jakarta.validation.constraints.Min
import org.hibernate.validator.constraints.Length
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import ru.kaplaan.authserver.service.AuthService
import ru.kaplaan.authserver.service.UserInfoService
import ru.kaplaan.authserver.web.dto.authentication.AuthenticationDto
import ru.kaplaan.authserver.web.dto.refreshToken.RefreshTokenDto
import ru.kaplaan.authserver.web.dto.response.JwtResponse
import ru.kaplaan.authserver.web.dto.response.MessageResponse
import ru.kaplaan.authserver.web.dto.user.UserDto
import ru.kaplaan.authserver.web.dto.user.UserIdentificationDto
import ru.kaplaan.authserver.web.mapper.toDto
import ru.kaplaan.authserver.web.mapper.toEntity
import ru.kaplaan.authserver.web.mapper.toUnauthenticatedToken
import ru.kaplaan.authserver.web.validation.OnCreate
import ru.kaplaan.authserver.web.validation.OnRecovery


@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
    private val userInfoService: UserInfoService
) {

    private val log = LoggerFactory.getLogger(AuthController::class.java)

    @PostMapping("/registration")
    fun registerUser(
        @RequestBody @Validated(OnCreate::class)
        userDto: UserDto,
    ): ResponseEntity<MessageResponse> {
        authService.register(userDto.toEntity())
        log.info("Код подтверждения для пользователя ${userDto.username.uppercase()} отправлен на почту")

        return MessageResponse("Код подтверждения отправлен вам на почту").let {
            ResponseEntity.status(HttpStatus.OK).body(it)
        }
    }

    @PostMapping("/login")
    fun login(
        @RequestBody @Validated(OnCreate::class)
        userIdentificationDto: UserIdentificationDto
    ): JwtResponse =
        authService.authenticate(userIdentificationDto.toEntity())

    @GetMapping("/activation/{code}")
    fun activateAccount(
        @PathVariable @Length(min = 1)
        code: String,
    ): String =
        authService.activateAccount(code).let {
            "Аккаунт успешно активирован"
        }

    @PostMapping("/recovery")
    fun passwordRecovery(
        @RequestBody(required = true) @Validated(OnRecovery::class)
        userIdentificationDto: UserIdentificationDto,
    ): MessageResponse {
        authService.passwordRecovery(userIdentificationDto.toEntity())
        return MessageResponse("Код восстановления отправлен Вам на почту")
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody @Validated(OnCreate::class) authenticationDto: AuthenticationDto): AuthenticationDto =
        authService.authenticate(authenticationDto.toUnauthenticatedToken()).toDto()


    @PostMapping("/refresh")
    fun getNewRefreshToken(
        @RequestBody @Validated(OnCreate::class) refreshTokenDto: RefreshTokenDto
    ): JwtResponse = authService.refresh(refreshTokenDto.refreshToken)


    @GetMapping("/{userId}")
    fun getUsernameByUserId(
        @Validated @Min(0, message = "Минимальное Id пользователя - 0!")
        @PathVariable userId: Long
    ): String = userInfoService.getUsernameByUserId(userId)

}
