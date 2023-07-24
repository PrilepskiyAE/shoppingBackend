package com.prilepskiy.shoppingBackend

import com.prilepskiy.shoppingBackend.domain.repository.CategoryRepository
import com.prilepskiy.shoppingBackend.domain.repository.DisheRepository
import com.prilepskiy.shoppingBackend.domain.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories


@SpringBootApplication
@EnableMongoRepositories("com.prilepskiy.shoppingBackend.domain.repository")
class ShoppingBackendApplication

fun main(args: Array<String>) {
	runApplication<ShoppingBackendApplication>(*args)
}
