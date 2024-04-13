package ru.kaplaan.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ConsumerServerApplication

fun main(args: Array<String>) {
    runApplication<ConsumerServerApplication>(*args)
}
