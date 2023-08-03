package com.prilepskiy.shoppingBackend.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.prilepskiy.shoppingBackend.domain.model.HttpResult
import com.prilepskiy.shoppingBackend.domain.service.JwtService
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets

@Component
class JwtWebFilter: WebFilter {


    fun writeErrorMessage(response: ServerHttpResponse, status: HttpStatus, msg: String?): Mono<Void> {
        response.headers.contentType = MediaType.APPLICATION_JSON
        val mapper = ObjectMapper()
        val body = mapper.writeValueAsString(HttpResult(status.value(), msg?:DEFAULT_MESSAGE, null))
        val dataBuffer: DataBuffer = response.bufferFactory().wrap(body.toByteArray(StandardCharsets.UTF_8))
        return response.writeWith(Mono.just(dataBuffer))
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request: ServerHttpRequest = exchange.request
        val response = exchange.response
        val path = request.path.value()
        if (path.contains("/auth/login") || path.contains("/auth/singup")) return chain.filter(exchange)
        val auth = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        if (auth == null) {
            return this.writeErrorMessage(response, HttpStatus.NOT_ACCEPTABLE, "«Нет маркировки»")
        }else if (!auth.startsWith(JwtService.TOKEN_PREFIX)) {
            return this.writeErrorMessage(response, HttpStatus.NOT_ACCEPTABLE, "Токен не  ${JwtService.TOKEN_PREFIX}  Начинать");
        }
        val token: String = auth.replace(JwtService.TOKEN_PREFIX, "")
        try {
            exchange.attributes["token"] = token
        } catch (e: Exception) {
            return writeErrorMessage(response, HttpStatus.INTERNAL_SERVER_ERROR, e.message)
        }
        return chain.filter(exchange)
    }
    companion object{
        const val  DEFAULT_MESSAGE:String=""
    }
}