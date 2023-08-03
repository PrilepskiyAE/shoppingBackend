package com.prilepskiy.shoppingBackend.domain.model

import com.prilepskiy.shoppingBackend.data.User
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

data class HttpResult(val code: Int = 200, val message: String, val data: User?)