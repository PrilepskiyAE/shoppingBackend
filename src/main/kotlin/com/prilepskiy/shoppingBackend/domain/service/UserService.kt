package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.data.User
import com.prilepskiy.shoppingBackend.domain.repository.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserService(private val userRepository: UserRepository) {
    @PostConstruct
    fun initDate() {}
    private fun save(user: User) = userRepository.save(user)
    fun getUserById(id:String) =userRepository.findById(id)

    fun getUserByToken(token: String) = userRepository.findAll().filter {
        it.token==token
    }
}