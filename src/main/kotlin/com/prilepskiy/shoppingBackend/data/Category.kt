package com.prilepskiy.shoppingBackend.data

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Category (
    val id: String? = null,
    val image_url: String ="",
    val name: String=""
)