package ru.kaplaan.api.service.consumerServer

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.consumerServer.details.CompanyDataDto
import ru.kaplaan.api.web.dto.consumerServer.details.UserDataDto


@Service
interface DetailsService {

    fun saveCompanyData(companyDataDto: Mono<CompanyDataDto>): Mono<ResponseEntity<String>>

    fun getCompanyDataByCompanyName(companyName: String): Mono<ResponseEntity<CompanyDataDto>>

    fun saveUserData(userDataDto: Mono<UserDataDto>): Mono<ResponseEntity<String>>

    fun getUserDataByUsername(username: String): Mono<ResponseEntity<UserDataDto>>
}