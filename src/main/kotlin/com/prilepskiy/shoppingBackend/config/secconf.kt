package com.prilepskiy.shoppingBackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration {
    private  val ENCODED_PASSWORD = "$2a$10\$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2"
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http {
            authorizeExchange {
                authorize("/auth/singup", permitAll)
                authorize("/auth/login", permitAll)
                authorize("/auth/login", permitAll)
                authorize("/", permitAll)
                authorize("/css/**", permitAll)
                authorize("/Dishe", hasAuthority("ROLE_USER"))
            }
            formLogin {
                loginPage = "/auth/login"
            }
            csrf{
                disable()
            }
        }
    }

    @Bean
    fun userDetailsService(): ReactiveUserDetailsService {
        val userDetails: User? = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build()
        return MapReactiveUserDetailsService(userDetails)
    }
}