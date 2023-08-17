package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.data.UserD
import com.prilepskiy.shoppingBackend.domain.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun save(userD: UserD) = userRepository.save(userD).thenMany(userRepository.findAll())
    fun getUserById(id: String) = userRepository.findById(id)

    fun getUserByEmail(email: String) = userRepository.findAll().filter {
        it.email == email
    }.last()

    fun getUserByToken(token: String) = userRepository.findAll().filter {
        it.token == token
    }.last()
    fun getUserByName(name: String) = userRepository.findAll().filter {
        it.userName == name
    }.last()
}