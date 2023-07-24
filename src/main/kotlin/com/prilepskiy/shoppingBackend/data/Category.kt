package com.prilepskiy.shoppingBackend.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document
data class Category (
    @Id
    val id: String? = null,
    val image_url: String ="",
    val name: String=""
)