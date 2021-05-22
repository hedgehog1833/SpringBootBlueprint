package com.dammenhayn.blueprint.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

  private LocalDateTime timestamp;
  private List<String> messages = new ArrayList<>();

  public ErrorResponse(List<String> messages) {
    this.timestamp = LocalDateTime.now();
    this.messages  = messages;
  }

}
