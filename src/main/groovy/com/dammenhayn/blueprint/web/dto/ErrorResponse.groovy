package com.dammenhayn.blueprint.web.dto

import java.time.LocalDateTime

class ErrorResponse {

  LocalDateTime timestamp
  List<String> messages

  ErrorResponse(List<String> messages) {
    this.timestamp = LocalDateTime.now()
    this.messages  = messages
  }
}
