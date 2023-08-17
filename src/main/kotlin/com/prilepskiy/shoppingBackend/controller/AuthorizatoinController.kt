package com.prilepskiy.shoppingBackend.controller


import com.prilepskiy.shoppingBackend.data.UserD
import com.prilepskiy.shoppingBackend.domain.model.HttpResult
import com.prilepskiy.shoppingBackend.domain.model.LoginResponse
import com.prilepskiy.shoppingBackend.domain.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


//не знаю возможно переделаю spring security
@RestController
@RequestMapping("/auth")
class AuthorizatoinController(private val userService: UserService) {

    @PostMapping("/singup")
    @ResponseBody
    fun registration(
            @RequestHeader userName: String,
            @RequestHeader password: String,
            @RequestHeader avatarUrl: String,
            @RequestHeader email: String,
    ) = userService.save(
            UserD(
                    userName = userName,
                    password = password,
                    avatarUrl = avatarUrl,
                    email = email,
                    authorities = ""
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
                userService.save(it)
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
    fun getUser(@RequestHeader token: String): Mono<HttpResult> = userService.getUserByToken(token).map {
        HttpResult(200,"",it)
    }
    companion object{
        const val  HTTP_LOGIN_OK="«Успешно вошел в систему»"
        const val HTTP_LOGIN_ERROR="«Вход в систему не удался»"
        const val HTTP_SIGNUP_OK="«Регистрация прошла успешно»"
        const val HTTP_SIGNUP_ERROR="«Регистрация в систему не удался»"
    }
}