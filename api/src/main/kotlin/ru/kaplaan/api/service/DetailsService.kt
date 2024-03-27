package ru.kaplaan.api.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.details.CompanyDetailsDto
import ru.kaplaan.api.web.dto.details.UserDetailsDto


@Service
interface DetailsService {


    fun saveCompanyDetails(companyDetailsDto: Mono<CompanyDetailsDto>): Mono<ResponseEntity<String>>

    fun getCompanyDetailsByCompanyName(companyName: Mono<String>): Mono<ResponseEntity<CompanyDetailsDto>>

    fun saveUserDetails(userDetailsDto: Mono<UserDetailsDto>): Mono<ResponseEntity<String>>

    fun getUserDetailsByUsername(username: Mono<String>): Mono<UserDetailsDto>
}