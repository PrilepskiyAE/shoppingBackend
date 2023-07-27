package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.data.Dishe
import com.prilepskiy.shoppingBackend.domain.repository.DisheRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping

@Service
class DisheService(private val disheRepository: DisheRepository) {

    fun getDishe() = disheRepository.deleteAll().thenMany(disheRepository
            .saveAll(listOf(
                    Dishe(
                            description = "",
                            categoryName = "Fallout",
                            image_url = "",
                            name = "test",
                            price = 0,
                            weight=0

                    ),
                    Dishe(
                            description = "",
                            categoryName = "Fallout",
                            image_url = "",
                            name = "test1",
                            price = 0,
                            weight=0

                    ),
                    Dishe(
                            description = "",
                            categoryName = "Супы",
                            image_url = "",
                            name = "test1",
                            price = 0,
                            weight=0

                    )

            ))).thenMany(disheRepository.findAll())

    fun getDisheById(id:String) =disheRepository.findById(id)

    fun getDisheByCategory(categoryName: String) = disheRepository.findAll().filter {
        it.categoryName==categoryName
    }

    fun getDisheByCategory(categoryName: String,id: String) = disheRepository.findAll().filter {
        it.categoryName==categoryName
    }.filter { it.id==id }
}