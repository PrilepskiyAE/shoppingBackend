package com.prilepskiy.shoppingBackend.domain.model

data class UserDTO (
    val id:String?=null,
    val token: String,
    val userName: String,
    val avatarUrl: String,
    val email: String,
    val favoriteId:List<String> = listOf(),
    val historyId:List<String> = listOf()
)