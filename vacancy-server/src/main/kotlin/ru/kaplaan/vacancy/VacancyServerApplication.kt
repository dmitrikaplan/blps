package ru.kaplaan.vacancy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VacancyServerApplication

fun main(args: Array<String>) {
    runApplication<VacancyServerApplication>(*args)
}
