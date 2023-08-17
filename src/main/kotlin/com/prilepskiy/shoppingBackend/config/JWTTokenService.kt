package com.prilepskiy.shoppingBackend.config

import com.nimbusds.jose.*
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.security.core.GrantedAuthority
import java.time.Period
import java.util.*
import java.util.stream.Collectors


class JWTTokenService {
    val DEFAULT_SECRET = "qwertyuiopasdfghjklzxcvbnmqwerty"
    fun generateToken(subject: String?, credentials: Any?, authorities: Collection<GrantedAuthority?>): String {
        val signedJWT: SignedJWT
        val claimsSet: JWTClaimsSet

        claimsSet = JWTClaimsSet.Builder()
                .subject(subject)
                .issuer("rapha.io")
                .expirationTime(Date(expiration))
                .claim("roles", authorities
                        .stream()
                        .map<GrantedAuthority> { obj: Any? -> GrantedAuthority::class.java.cast(obj) }
                        .map<String> { obj: GrantedAuthority -> obj.authority }
                        .collect(Collectors.joining(",")))
                .build()
        signedJWT = SignedJWT(JWSHeader(JWSAlgorithm.HS256), claimsSet)
        try {
            signedJWT.sign(getSigner())
        } catch (e: JOSEException) {
            e.printStackTrace()
        }
        return signedJWT.serialize()
    }

    private val expiration: Long
        private get() = Date().toInstant()
                .plus(Period.ofDays(1))
                .toEpochMilli()

    private var signer: JWSSigner? = null

    fun JWTCustomSigner() {
        try {
            signer = MACSigner(DEFAULT_SECRET)
        } catch (e: KeyLengthException) {
            signer = null
        }
    }

    private fun getSigner(): JWSSigner? {
        return signer
    }
}