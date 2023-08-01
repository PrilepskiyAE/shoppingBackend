package com.prilepskiy.shoppingBackend


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories



@SpringBootApplication
@EnableMongoRepositories("com.prilepskiy.shoppingBackend.domain.repository")
class ShoppingBackendApplication

fun main(args: Array<String>) {
    runApplication<ShoppingBackendApplication>(*args)
}
