package ru.kaplaan.api.web.controller.authServer

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotBlank
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import ru.kaplaan.api.service.authServer.AuthService
import ru.kaplaan.api.web.dto.authServer.refresh_token.RefreshTokenDto
import ru.kaplaan.api.web.dto.authServer.response.JwtResponse
import ru.kaplaan.api.web.dto.authServer.response.MessageResponse
import ru.kaplaan.api.web.dto.authServer.user.Role
import ru.kaplaan.api.web.dto.authServer.user.UserDto
import ru.kaplaan.api.web.dto.authServer.user.UserIdentificationDto
import ru.kaplaan.api.web.validation.OnCreate
import ru.kaplaan.api.web.validation.OnRecovery
import javax.management.relation.RoleNotFoundException

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth Controller", description = "Контроллер аутентификации")
class AuthController(
    private val authService: AuthService
) {

    private val log = LoggerFactory.getLogger(AuthController::class.java)


    @PostMapping("/registration/{role}")
    @Operation(summary = "Регистрация пользователя")
    fun registerCompany(
        @RequestBody @Validated(OnCreate::class)
        userDto: Mono<UserDto>,
        @PathVariable role: String
    ): Mono<ResponseEntity<MessageResponse>> {
        return authService.register(
            userDto.map {
                it.apply {
                    this.role = when(role.uppercase()){

                        "USER" -> Role.ROLE_USER

                        "COMPANY" -> Role.ROLE_COMPANY

                        else -> throw RoleNotFoundException()
                    }
                }
            }
        )
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя")
    fun login(
        @RequestBody @Validated(OnCreate::class)
        userIdentificationDto: Mono<UserIdentificationDto>,
    ): Mono<ResponseEntity<JwtResponse>> = authService.login(userIdentificationDto)

    @GetMapping("/activation/{activationCode}")
    @Operation(summary = "Активация аккаунта пользователя")
    fun activateAccount(
        @PathVariable @Validated @NotBlank
        @Parameter(description = "код активации аккаунта", required = true)
        activationCode: String,
    ): Mono<ResponseEntity<String>> = authService.activateAccount(activationCode)

    @PostMapping("/recovery")
    @Operation(summary = "Восстановление доступа пользователя", hidden = true)
    fun passwordRecovery(
        @RequestBody @Validated(OnRecovery::class)
        userIdentificationDto: Mono<UserIdentificationDto>,
    ): Mono<ResponseEntity<MessageResponse>> = authService.passwordRecovery(userIdentificationDto)

    @Operation(summary = "Обновление jwt access токена")
    @PostMapping("/refresh")
    fun refresh(
        @RequestBody @Validated refreshTokenDto: Mono<RefreshTokenDto>
    ): Mono<ResponseEntity<JwtResponse>> = authService.refresh(refreshTokenDto)
}
