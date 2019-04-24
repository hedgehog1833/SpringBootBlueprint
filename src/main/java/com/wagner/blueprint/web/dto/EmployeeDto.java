package com.wagner.blueprint.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto extends AbstractEntityDto {

  private String    firstName;
  private String    lastName;
  private String    email;
  private boolean   isActive;
  private LocalDate birthday;
  private String    job;
  private String    careerLevel;

}
