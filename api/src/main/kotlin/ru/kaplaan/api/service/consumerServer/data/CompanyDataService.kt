package ru.kaplaan.api.service.consumerServer.data

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.kaplaan.api.web.dto.consumerServer.data.CompanyDataDto


@Service
interface CompanyDataService {

    fun saveCompanyData(companyDataDto: Mono<CompanyDataDto>): Mono<ResponseEntity<CompanyDataDto>>

    fun updateCompanyData(companyDataDto: Mono<CompanyDataDto>): Mono<ResponseEntity<CompanyDataDto>>

    fun getCompanyDataByCompanyId(companyId: Long): Mono<ResponseEntity<CompanyDataDto>>
}