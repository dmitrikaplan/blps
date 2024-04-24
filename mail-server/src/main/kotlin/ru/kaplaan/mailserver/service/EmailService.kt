package ru.kaplaan.mailserver.service


import org.springframework.stereotype.Service
import ru.kaplaan.mailserver.web.dto.vacancyResponse.VacancyResponseEmailDto

@Service
interface EmailService {
    fun activateUserByEmail(emailTo: String, username: String, activationCode: String)

    fun notifyAboutUpdateVacancyResponseStatus(vacancyResponseEmailDto: VacancyResponseEmailDto)
}
