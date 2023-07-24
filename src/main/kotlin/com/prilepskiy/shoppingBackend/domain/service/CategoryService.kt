package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.data.Category
import com.prilepskiy.shoppingBackend.data.Dishe
import com.prilepskiy.shoppingBackend.domain.repository.CategoryRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    @PostConstruct
    fun initDate() {}
    private fun save(category: Category) = categoryRepository.save(category)

}