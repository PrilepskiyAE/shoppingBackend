package com.prilepskiy.shoppingBackend.domain.repository

import com.prilepskiy.shoppingBackend.data.Category
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CategoryRepository  : ReactiveCrudRepository<Category, String>