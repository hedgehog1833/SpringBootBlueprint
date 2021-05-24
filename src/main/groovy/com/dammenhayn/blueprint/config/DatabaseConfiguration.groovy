package com.dammenhayn.blueprint.config

import org.modelmapper.ModelMapper
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = ["com.dammenhayn.blueprint.model.repository"])
@EntityScan(basePackages = ["com.dammenhayn.blueprint.model.entity"])
class DatabaseConfiguration {

  @Bean
  AuditorAware<String> auditorAware() {
    return () -> Optional.of("ANONYMOUS")
  }

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper()
  }
}
