package com.dammenhayn.blueprint.web.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

import java.time.LocalDate

class EmployeeRequestDto {

  @NotBlank
  String firstName

  @NotBlank
  String lastName

  @Email
  @NotBlank
  String email

  @NotBlank
  String team

  boolean active
  LocalDate birthday
  String job
  String careerLevel
}
