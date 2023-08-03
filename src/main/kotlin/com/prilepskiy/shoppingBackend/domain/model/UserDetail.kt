package com.prilepskiy.shoppingBackend.domain.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors
import java.util.stream.Stream

data class UserDetail(
        private val id:String?=null,
        private var userName: String,
        private  var password:String,
        private  val authorities:String

): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
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

    override fun isAccountNonExpired(): Boolean =true

    override fun isAccountNonLocked(): Boolean =true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean =true
}