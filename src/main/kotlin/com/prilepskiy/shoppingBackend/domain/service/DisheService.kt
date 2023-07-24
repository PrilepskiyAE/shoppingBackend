package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.data.Dishe
import com.prilepskiy.shoppingBackend.domain.repository.DisheRepository
import org.springframework.stereotype.Service

@Service
class DisheService(private val disheRepository: DisheRepository) {

    private fun save(dishe: Dishe) = disheRepository.save(dishe)
    fun getDishe() = disheRepository.findAll()
    fun getDisheById(id:String) =disheRepository.findById(id)

    fun getDisheByCategory(categoryName: String) = disheRepository.findAll().filter {
        it.category.name==categoryName
    }
}