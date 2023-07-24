package com.prilepskiy.shoppingBackend.domain.repository

import com.prilepskiy.shoppingBackend.data.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository : ReactiveMongoRepository<User, String>