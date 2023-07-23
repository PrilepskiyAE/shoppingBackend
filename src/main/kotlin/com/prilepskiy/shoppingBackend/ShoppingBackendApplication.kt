package com.prilepskiy.shoppingBackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShoppingBackendApplication

fun main(args: Array<String>) {
	runApplication<ShoppingBackendApplication>(*args)
}
