package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.data.User
import com.prilepskiy.shoppingBackend.domain.repository.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {
    fun save(user: User) = userRepository.save(user).thenMany(userRepository.findAll())
    fun getUserById(id: String) = userRepository.findById(id)

    fun getUserByEmail(email: String) = userRepository.findAll().filter {
        it.email == email
    }.last()

    fun getUserByToken(token: String) = userRepository.findAll().filter {
        it.token == token
    }.last()
}