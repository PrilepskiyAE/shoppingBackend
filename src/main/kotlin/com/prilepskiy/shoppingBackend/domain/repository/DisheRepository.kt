package com.prilepskiy.shoppingBackend.domain.repository

import com.prilepskiy.shoppingBackend.data.Dishe
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface DisheRepository  : ReactiveCrudRepository<Dishe, String>