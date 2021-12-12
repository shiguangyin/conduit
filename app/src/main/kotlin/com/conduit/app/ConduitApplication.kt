package com.conduit.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class ConduitApplication

fun main(args: Array<String>) {
    runApplication<ConduitApplication>(*args)
}
