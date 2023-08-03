package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.data.User
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: UserService,
) {
    fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.userName)
            .claim("token",user.token)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): Mono<User>? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val token = jwt.claims["token"] as String
            userService.getUserByToken(token)
        } catch (e: Exception) {
            null
        }
    }
}