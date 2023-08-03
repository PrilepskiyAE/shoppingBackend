package com.prilepskiy.shoppingBackend.controller

import com.prilepskiy.shoppingBackend.data.User
import com.prilepskiy.shoppingBackend.domain.model.HttpResult
import com.prilepskiy.shoppingBackend.domain.model.LoginResponse
import com.prilepskiy.shoppingBackend.domain.model.UserDTO
import com.prilepskiy.shoppingBackend.domain.service.DisheService
import com.prilepskiy.shoppingBackend.domain.service.JwtService
import com.prilepskiy.shoppingBackend.domain.service.UserService
import kotlinx.coroutines.reactive.awaitLast
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//не знаю возможно переделаю spring security
@RestController
@RequestMapping("/auth")
class AuthorizatoinController(private val userService: UserService,private val jwtService: JwtService) {

    @PostMapping("/singup")
    @ResponseBody
    fun registration(
            @RequestHeader userName: String,
            @RequestHeader password: String,
            @RequestHeader avatarUrl: String,
            @RequestHeader email: String,
    ) = userService.save(
            User(
                    userName = userName,
                    password = password,
                    avatarUrl = avatarUrl,
                    email = email,
                    token = jwtService.generateToken(userName)?:""
            )
    ).map {
        HttpResult(
                HttpStatus.OK.value(),
                HTTP_SIGNUP_OK,
                it)
    }.onErrorResume {
        Mono.just(
            HttpResult(
                    HttpStatus.UNAUTHORIZED.value(),
                    "$HTTP_SIGNUP_ERROR $it",
                    null))}



    @PostMapping("login")
    @ResponseBody
    fun login(
            @RequestHeader userName: String,
            @RequestHeader password: String,
    ) = Mono.just(
            LoginResponse(
                    userName = userName,
                    password = password,
            ))
            .flatMap {
                userService.getUserByName(it.userName)
            }.filter {
                it.password == password
            }

            .map {
                userService.save(it.copy(token = jwtService.generateToken(it.userName)?:""))
                HttpResult(
                        HttpStatus.OK.value(),
                        HTTP_LOGIN_OK,
                        it)
            }
            .onErrorResume {
                Mono.empty()
            }
            .switchIfEmpty(Mono.just(
                    HttpResult(
                            HttpStatus.UNAUTHORIZED.value(),
                            HTTP_LOGIN_ERROR,
                            null)))


    @GetMapping("/getUser")
    @ResponseBody
    fun getUser(@RequestHeader token: String): Mono<UserDTO> = userService.getUserByToken(token).map {
        UserDTO(it.id, it.token, it.userName, it.password, it.avatarUrl, it.email, it.favoriteId, it.historyId)
    }
    companion object{
        const val  HTTP_LOGIN_OK="«Успешно вошел в систему»"
        const val HTTP_LOGIN_ERROR="«Вход в систему не удался»"
        const val HTTP_SIGNUP_OK="«Регистрация прошла успешно»"
        const val HTTP_SIGNUP_ERROR="«Регистрация в систему не удался»"
    }
}