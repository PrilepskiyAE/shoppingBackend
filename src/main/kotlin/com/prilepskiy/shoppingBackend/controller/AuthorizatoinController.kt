package com.prilepskiy.shoppingBackend.controller

import com.prilepskiy.shoppingBackend.data.User
import com.prilepskiy.shoppingBackend.domain.model.UserDTO
import com.prilepskiy.shoppingBackend.domain.service.DisheService
import com.prilepskiy.shoppingBackend.domain.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping("/auth")
class AuthorizatoinController(private val userService: UserService) {

    @GetMapping("/registration")
    @ResponseBody
    fun registration(user: UserDTO): Mono<UserDTO> = userService.save(User(
            id = user.id,
            token = user.token,
            userName = user.userName,
            avatarUrl = user.avatarUrl,
            email = user.email,
    )).thenMany(userService.getUserByToken(user.token).map { UserDTO(
            user.id,
            token = user.token,
            userName = user.userName,
            avatarUrl = user.avatarUrl,
            email = user.email,
    ) }).last()

    @GetMapping
    @ResponseBody
    fun auth(user: UserDTO):UserDTO{
        return TODO()
    }
    @GetMapping("/getUser")
    @ResponseBody
    fun getUser(token:String):UserDTO{
        return TODO()
    }
}