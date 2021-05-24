package com.dammenhayn.blueprint.model.exception

import org.springframework.validation.FieldError

class RestRequestValidationException extends RuntimeException {

  List<FieldError> fieldErrors = new ArrayList<>()

  private RestRequestValidationException(String message) {
    super(message)
  }

  RestRequestValidationException(String message, List<FieldError> fieldErrors) {
    this(message)
    this.fieldErrors = fieldErrors
  }
}
