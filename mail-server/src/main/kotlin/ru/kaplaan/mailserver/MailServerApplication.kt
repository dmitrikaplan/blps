package ru.kaplaan.mailserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
class MailServerApplication

fun main(args: Array<String>) {
    runApplication<MailServerApplication>(*args)
}
