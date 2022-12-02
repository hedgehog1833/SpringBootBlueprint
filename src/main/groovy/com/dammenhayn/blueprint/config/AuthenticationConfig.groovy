package com.dammenhayn.blueprint.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS
import static org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL

@EnableWebSecurity(debug = true)
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class AuthenticationConfig {

  AuthenticationConfig() {
    SecurityContextHolder.strategyName = MODE_INHERITABLETHREADLOCAL
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(STATELESS)
        .and()
        .authorizeRequests { customizer ->
          customizer
              .anyRequest().fullyAuthenticated()
        }
        .userDetailsService(inMemoryUserDetailsManager())
        .httpBasic()
        .and().build()
  }

  @Bean
  InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
    new InMemoryUserDetailsManager([User.withUsername("user")
                                        .password(encoder.encode("password"))
                                        .roles("TEST")
                                        .build()])
  }
}
