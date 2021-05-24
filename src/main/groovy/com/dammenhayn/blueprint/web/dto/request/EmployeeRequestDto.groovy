package com.dammenhayn.blueprint.web.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import java.time.LocalDate

class EmployeeRequestDto {

  @NotBlank
  String firstName

  @NotBlank
  String lastName

  @Email
  @NotEmpty
  String email

  @NotBlank
  String teamName

  boolean active
  LocalDate birthday
  String job
  String careerLevel
}
