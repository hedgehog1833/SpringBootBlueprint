package com.dammenhayn.blueprint.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

import static com.dammenhayn.blueprint.config.Endpoints.ACTUATOR_ENDPOINTS
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS

@Configuration
@EnableWebSecurity
class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf { it.disable() } // todo NilsD
        .sessionManagement { it.sessionCreationPolicy(STATELESS) }
        .exceptionHandling {}
        .authorizeHttpRequests { registry ->
          registry
              .requestMatchers(ACTUATOR_ENDPOINTS).permitAll()
              .requestMatchers("/**").permitAll()
              .anyRequest().denyAll()
        }
    return http.build()
  }
}
