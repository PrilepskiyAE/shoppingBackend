package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.data.Dishe
import com.prilepskiy.shoppingBackend.data.User
import com.prilepskiy.shoppingBackend.domain.repository.DisheRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class DisheService(private val disheRepository: DisheRepository) {
    @PostConstruct
    fun initDate() {}
    private fun save(dishe: Dishe) = disheRepository.save(dishe)
}