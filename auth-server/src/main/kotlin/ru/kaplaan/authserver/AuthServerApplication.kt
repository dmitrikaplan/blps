package ru.kaplaan.authserver

import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import ru.kaplaan.authserver.domain.entity.user.Role
import ru.kaplaan.authserver.domain.entity.user.User
import ru.kaplaan.authserver.service.AuthService

@SpringBootApplication
@EnableTransactionManagement
@ConfigurationPropertiesScan(basePackages = ["ru.kaplaan.authserver.domain.properties"])
class AuthServerApplication(private val authService: AuthService){

	@Value("\${accountant.username}")
	private lateinit var username: String

	@Value("\${accountant.password}")
	private lateinit var password: String

	@Value("\${accountant.email}")
	private lateinit var email: String

	private val log = LoggerFactory.getLogger(javaClass)

	@PostConstruct
	fun initInMemoryUsers(){
		User().apply {
			username = this@AuthServerApplication.username
			password = this@AuthServerApplication.password
			email = this@AuthServerApplication.email
			role = Role.ROLE_ADMIN
		}.let { user ->
			runCatching {
				authService.register(user)
			}.also {
				if(it.isFailure){
					log.error(it.exceptionOrNull()?.message)
				}
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<AuthServerApplication>(*args)
}
