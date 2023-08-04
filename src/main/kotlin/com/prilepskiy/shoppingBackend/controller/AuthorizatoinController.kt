package com.prilepskiy.shoppingBackend.controller

import com.prilepskiy.shoppingBackend.data.User
import com.prilepskiy.shoppingBackend.domain.model.UserDTO
import com.prilepskiy.shoppingBackend.domain.service.DisheService
import com.prilepskiy.shoppingBackend.domain.service.UserService
import kotlinx.coroutines.reactive.awaitLast
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//не знаю возможно переделаю spring security
@RestController
@RequestMapping("auth")
class AuthorizatoinController(private val userService: UserService) {

    @PostMapping("reg")
    @ResponseBody
    fun registration(
        @RequestHeader userName: String,
        @RequestHeader password: String,
        @RequestHeader avatarUrl: String,
        @RequestHeader email: String,
    ): Mono<UserDTO> = userService.save(
        User(
            userName = userName,
            password = password,
            avatarUrl = avatarUrl,
            email = email,
        )
    ).thenMany(userService.getUserByEmail(email)).last().map {
        UserDTO(it.id, it.token, it.userName, it.password, it.avatarUrl, it.email, it.favoriteId, it.historyId)
    }

    @GetMapping
    @ResponseBody
    fun auth(user: UserDTO): UserDTO {
        return TODO()
    }

    @GetMapping("/getUser")
    @ResponseBody
    fun getUser(@RequestHeader token: String): Mono<UserDTO> = userService.getUserByToken(token).map {
        UserDTO(it.id, it.token, it.userName,it.password, it.avatarUrl, it.email, it.favoriteId, it.historyId)
    }
}