package ru.kaplaan.api.service.consumerServer

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.consumerServer.data.CompanyDataDto
import ru.kaplaan.api.web.dto.consumerServer.data.UserDataDto


@Service
interface DataService {

    fun saveCompanyData(companyDataDto: Mono<CompanyDataDto>): Mono<ResponseEntity<CompanyDataDto>>

    fun updateCompanyData(companyDataDto: Mono<CompanyDataDto>): Mono<ResponseEntity<CompanyDataDto>>

    fun getCompanyDataByCompanyId(companyId: Long): Mono<ResponseEntity<CompanyDataDto>>

    fun saveUserData(userDataDto: Mono<UserDataDto>): Mono<ResponseEntity<UserDataDto>>

    fun updateUserData(userDataDto: Mono<UserDataDto>): Mono<ResponseEntity<UserDataDto>>

    fun getUserDataByUserId(userId: Long): Mono<ResponseEntity<UserDataDto>>
}