package ru.kaplaan.authserver.domain.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import ru.kaplaan.authserver.domain.provider.EmailAuthenticationProvider
import ru.kaplaan.authserver.domain.provider.JwtAuthenticationProvider
import ru.kaplaan.authserver.repository.UserRepository
import ru.kaplaan.authserver.service.PrivilegeService

@Configuration
class AuthServiceConfig(
    private val userRepository: UserRepository,
    private val privilegeService: PrivilegeService
) {

    @Bean
    fun authenticationManager(): AuthenticationManager =
        ProviderManager(authenticationProvider(), jwtAuthenticationProvider(), emailAuthenticationProvider())

    @Bean
    fun userDetailsService(): UserDetailsService =
        UserDetailsService { username ->
            userRepository.findByUsername(username)
                ?.also {
                    it.privileges = privilegeService.getAllPrivilegesByRole(it.role)
                } ?: throw UsernameNotFoundException("Не найден пользователь по username!")
        }

    @Bean
    fun authenticationProvider(): AuthenticationProvider =
        DaoAuthenticationProvider().apply {
            setUserDetailsService(userDetailsService())
            setPasswordEncoder(passwordEncoder())
        }

    @Bean
    fun jwtAuthenticationProvider() =
        JwtAuthenticationProvider(userDetailsService())

    @Bean
    fun emailAuthenticationProvider() =
        EmailAuthenticationProvider(userRepository, passwordEncoder())

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()
}