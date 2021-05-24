package com.dammenhayn.blueprint.model.exception

enum ErrorMessages {

  VALIDATION_ERROR("There were errors validating your request. Please refer to the documentation for a valid request.")

  private final String errorMessage

  ErrorMessages(String errorMessage) {
    this.errorMessage = errorMessage
  }

  String toDisplayString() {
    return errorMessage
  }
}
