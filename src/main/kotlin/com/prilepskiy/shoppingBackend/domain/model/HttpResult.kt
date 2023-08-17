package com.prilepskiy.shoppingBackend.domain.model

import com.prilepskiy.shoppingBackend.data.UserD

data class HttpResult(val code: Int = 200, val message: String, val data: UserD?)
data class LoginResponse (val userName:String,val password:String)