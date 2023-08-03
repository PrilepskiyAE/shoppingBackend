package com.prilepskiy.shoppingBackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authorization.AuthenticatedAuthorizationManager.authenticated
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.invoke
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.context.SecurityContextRepository
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.anyExchange

@Configuration
@EnableWebFluxSecurity
class SecurityConfiguration (securityRepository: SecurityContextRepository){

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity,jwtWebFilter:JwtWebFilter): SecurityWebFilterChain {
        return http {
            authorizeExchange {
                authorize("/auth/login", permitAll)
                authorize("/auth/signup", permitAll)
                authorize("/", permitAll)
                authorize("/css/**", permitAll)
                authorize("/user/**", hasAuthority("ROLE_USER"))

            }

            addFilterAfter(jwtWebFilter, SecurityWebFiltersOrder.FIRST)
            formLogin {
                disable()
            }
            httpBasic{
                disable()
            }
            csrf {
                disable()
            }
            logout {
                disable()
            }
        }
    }


}