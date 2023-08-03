package com.prilepskiy.shoppingBackend.config

import com.prilepskiy.shoppingBackend.domain.service.JwtService
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.stream.Collectors
import java.util.stream.Stream;
@Component
class JwtAuthenticationManager(private val jwtService: JwtService) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> = Mono.just(authentication)
            .map { auth -> jwtService.parseToken(auth.credentials.toString()) }
            .onErrorResume { Mono.empty() }
            .map { claims ->
                UsernamePasswordAuthenticationToken(claims.getSubject(), null, Stream.of(claims.get(JwtService.AUTHORITIES))
                        .peek { info -> print("Информация о авторизации разрешения, $info") }
                        .map {
                            it as List<Map<String, String>>
                        }
                        .flatMap {  it.stream() }
                        .map { it["authority"] }
                        .map{ SimpleGrantedAuthority(it) }
                        .collect(Collectors.toList()))
            }
}