package com.prilepskiy.shoppingBackend.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.yaml.snakeyaml.tokens.AliasToken
@Document
data class User(
    @Id
    val id:String?=null,
    val token: String,
    val userName: String,
    val avatarUrl: String,
    val email: String,
    val favoriteId:List<String> = listOf(),
    val historyId:List<String> = listOf()
    )
