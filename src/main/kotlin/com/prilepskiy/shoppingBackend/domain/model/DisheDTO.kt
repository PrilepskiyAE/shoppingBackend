package com.prilepskiy.shoppingBackend.domain.model

import com.prilepskiy.shoppingBackend.data.Category
import org.springframework.data.annotation.Id

data class DisheDTO(
    val description: String = "",
    val id: String? = null,
    val categoryName: String,
    val image_url: String = "",
    val name: String = "",
    val price: Int = 0,
    val tegs: List<String> = listOf(),
    val weight: Int = 0
)