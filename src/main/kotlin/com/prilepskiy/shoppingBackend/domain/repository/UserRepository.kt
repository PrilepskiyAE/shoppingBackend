package com.prilepskiy.shoppingBackend.domain.repository

import com.prilepskiy.shoppingBackend.data.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface UserRepository : ReactiveCrudRepository<User, String>