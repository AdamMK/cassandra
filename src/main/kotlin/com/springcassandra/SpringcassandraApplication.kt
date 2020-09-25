package com.springcassandra

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringcassandraApplication

fun main(args: Array<String>) {
    runApplication<SpringcassandraApplication>(*args)
}
