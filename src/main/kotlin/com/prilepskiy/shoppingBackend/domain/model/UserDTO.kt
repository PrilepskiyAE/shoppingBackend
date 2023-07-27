package com.prilepskiy.shoppingBackend.domain.model

import java.util.UUID

data class UserDTO (
    val id:String?=null,
    val token: String=UUID.randomUUID(),
    val userName: String,
    val avatarUrl: String,
    val email: String,
    val favoriteId:List<String> = listOf(),
    val historyId:List<String> = listOf()
)