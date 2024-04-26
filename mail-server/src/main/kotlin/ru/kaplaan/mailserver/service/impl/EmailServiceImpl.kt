package ru.kaplaan.mailserver.service.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring6.SpringTemplateEngine
import ru.kaplaan.mailserver.domain.email.EmailMessage
import ru.kaplaan.mailserver.service.EmailService
import ru.kaplaan.mailserver.web.dto.auth.ActivateAccountByEmailDto
import ru.kaplaan.mailserver.web.dto.paymentOrder.PaymentOrderEmailDto
import ru.kaplaan.mailserver.web.dto.vacancyResponse.VacancyResponseEmailDto
import java.nio.charset.StandardCharsets

@Service
class EmailServiceImpl(
    private val mailSender: JavaMailSender,
    private val springTemplateEngine: SpringTemplateEngine
) : EmailService {

    @Value("\${service.host}")
    private lateinit var host: String

    @Value("\${spring.mail.username}")
    private lateinit var mail: String

    @Value("\${endpoint.activation}")
    private lateinit var activationEndpoint: String

    override fun activateUserByEmail(activateAccountByEmailDto: ActivateAccountByEmailDto) {
        val templateLocation = EmailMessage.REGISTRATION.pathOfTemplate
        val subject = EmailMessage.REGISTRATION.subject
        val context = Context().apply{
            setVariable("username", activateAccountByEmailDto.username)
            setVariable("activationLink", "$host/$activationEndpoint/${activateAccountByEmailDto.activationCode}")
            setVariable("subject", subject)
        }

        sendEmail(activateAccountByEmailDto.emailTo, subject, context, templateLocation)

    }

    override fun notifyAboutUpdateVacancyResponseStatus(vacancyResponseEmailDto: VacancyResponseEmailDto) {
        val templateLocation = EmailMessage.VACANCY_RESPONSE_STATUS.pathOfTemplate
        val subject = EmailMessage.VACANCY_RESPONSE_STATUS.subject
        val context = Context().apply {
            vacancyResponseEmailDto.let {
                setVariable("firstname",  it.firstname)
                setVariable("surname",  it.surname)
                setVariable("vacancyTitle", it.vacancyTitle)
                setVariable("status", it.status)
                setVariable("comment", it.comment)
            }
        }

        sendEmail(vacancyResponseEmailDto.email, subject, context, templateLocation)
    }

    override fun sendPaymentOrder(paymentOrderEmailDto: PaymentOrderEmailDto) {
        val templateLocation = EmailMessage.PAYMENT_ORDER.pathOfTemplate
        val subject = EmailMessage.PAYMENT_ORDER.subject
        val context = Context().apply {
            setVariable("paymentOrder", paymentOrderEmailDto)
        }

        sendEmail(paymentOrderEmailDto.email, subject, context, templateLocation)
    }

    private fun sendEmail(emailTo: String,  subject: String, context: Context, templateLocation: String) {
        val mailMessage = mailSender.createMimeMessage()
        val emailContent = springTemplateEngine.process(templateLocation, context)

        MimeMessageHelper(
            mailMessage,
            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
            StandardCharsets.UTF_8.name()
        ).apply {
            setFrom(mail)
            setSubject(subject)
            setTo(emailTo)
            setText(emailContent, true)
        }

        mailSender.send(mailMessage)
    }

}
