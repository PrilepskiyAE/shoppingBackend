package com.prilepskiy.shoppingBackend.controller

import com.prilepskiy.shoppingBackend.config.JwtAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class SecurityContextRepository(private val jwtAuthenticationManager: JwtAuthenticationManager): ServerSecurityContextRepository {
    override fun save(exchange: ServerWebExchange?, context: SecurityContext?): Mono<Void> = Mono.empty()

    override fun load(exchange: ServerWebExchange?): Mono<SecurityContext> {
        val token: String? = exchange?.getAttribute("token")
return jwtAuthenticationManager.authenticate(UsernamePasswordAuthenticationToken(token,token)).map {
    SecurityContextImpl(it)
}
    }
}