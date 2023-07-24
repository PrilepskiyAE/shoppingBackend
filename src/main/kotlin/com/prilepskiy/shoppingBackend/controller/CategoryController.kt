package com.prilepskiy.shoppingBackend.controller


import com.prilepskiy.shoppingBackend.domain.model.CategoryDTO
import com.prilepskiy.shoppingBackend.domain.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/category")
class CategoryController(private val categoryService: CategoryService) {
    @GetMapping
    fun getCategory() = categoryService.getCategory().map {
        CategoryDTO(it.id,it.image_url,it.name)
    }
}