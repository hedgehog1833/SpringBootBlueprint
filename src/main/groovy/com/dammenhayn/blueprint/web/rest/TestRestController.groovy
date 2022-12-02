package com.dammenhayn.blueprint.web.rest

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestRestController {

  @PreAuthorize("hasRole('ROLE_TEST')")
  @GetMapping(path = "/test")
  ResponseEntity<Void> get() {
    return ResponseEntity.ok().build()
  }
}
