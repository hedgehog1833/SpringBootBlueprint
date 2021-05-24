package com.dammenhayn.blueprint.config

import com.dammenhayn.blueprint.model.exception.RestRequestValidationException
import com.dammenhayn.blueprint.web.dto.ErrorResponse
import groovy.util.logging.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Slf4j
class AppExceptionHandler {

  @ExceptionHandler(value = RestRequestValidationException)
  ResponseEntity<ErrorResponse> handleBadRequests(RestRequestValidationException exception) {
    log.warn(exception.getMessage())
    return ResponseEntity.badRequest().body(createErrorResponse(exception))
  }

  private ErrorResponse createErrorResponse(RestRequestValidationException exception) {
    def messages = [exception.getMessage()]
    exception.fieldErrors.each {
      def errorMessage = "${it.field}: ${it.defaultMessage} (bad value was: ${it.rejectedValue})"
      log.warn(errorMessage)
      messages << errorMessage
    }
    return new ErrorResponse(messages)
  }
}
