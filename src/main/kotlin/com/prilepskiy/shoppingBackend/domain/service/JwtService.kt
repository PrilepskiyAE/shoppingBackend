package com.prilepskiy.shoppingBackend.domain.service

import com.prilepskiy.shoppingBackend.domain.model.UserDetail
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Service

class JwtService(private val userService: UserService) {



    fun generateToken(user: UserDetails)= Jwts.builder()
                .setSubject(user.username)
                .claim(AUTHORITIES, user.authorities)
                .signWith(SignatureAlgorithm.HS256, KEY)
                .setIssuer(ISSUER)
                .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(3600L))))
                .setIssuedAt(Date(System.currentTimeMillis()))
                .compact()

    fun generateToken(username: String): String? =userService.getUserByName(username).map {
          generateToken(UserDetail(it.id,it.userName,it.password, AUTHORITIES ))
      }.block()


    fun parseToken(token:String)= Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).body
    companion object{
        const val KEY="justAJwtSingleKey"
        const val AUTHORITIES="authorities"
        const val ISSUER="identity"
        const val TOKEN_PREFIX="Bearer "
    }
}