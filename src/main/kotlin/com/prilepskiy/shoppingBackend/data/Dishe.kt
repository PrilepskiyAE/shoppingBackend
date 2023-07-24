package com.prilepskiy.shoppingBackend.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Dishe (
    val description: String="",
    @Id
    val id: String?=null,
    val category: Category,
    val image_url: String="",
    val name: String="",
    val price: Int=0,
    val tegs: List<String> = listOf(),
    val weight: Int=0
)