package com.prilepskiy.shoppingBackend.config

import com.prilepskiy.shoppingBackend.data.User
import org.springframework.security.core.Authentication

fun Authentication.toUser(): User {
    return principal as User
}