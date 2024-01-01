package com.prilepskiy.shoppingBackend.data

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream


@Document
data class UserD(
        @Id
        private val id: String? = null,
        val token: String = UUID.randomUUID().toString(),
        val userName: String,
        private val password: String,
        private val avatarUrl: String,
        val email: String,
        private val authorities:String,
        private val favoriteId: List<String> = listOf(),
        private val historyId: List<String> = listOf()
) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> {
        return Stream.of(*authorities.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                .map { role: String? -> SimpleGrantedAuthority(role) }
                .collect(Collectors.toSet())
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return userName
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
