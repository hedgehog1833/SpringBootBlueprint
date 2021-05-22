package com.dammenhayn.blueprint.config;

import com.dammenhayn.blueprint.model.exception.RestRequestValidationException;
import com.dammenhayn.blueprint.web.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

  @ExceptionHandler(value = RestRequestValidationException.class)
  public ResponseEntity<ErrorResponse> handleBadRequests(RestRequestValidationException exception) {
    log.warn(exception.getMessage());
    return ResponseEntity.badRequest().body(createErrorResponse(exception));
  }

  private ErrorResponse createErrorResponse(RestRequestValidationException exception) {
    List<String> messages = new ArrayList<>();
    messages.add(exception.getMessage());
    exception.getFieldErrors().forEach(error -> {
      String errorMessage = error.getField() + ": " + error.getDefaultMessage() + " (bad value was: " + error.getRejectedValue() + ")";
      log.warn(errorMessage);
      messages.add(errorMessage);
    });

    return new ErrorResponse(messages);
  }

}
