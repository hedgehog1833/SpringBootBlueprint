package com.dammenhayn.blueprint.web.dto

import groovy.transform.EqualsAndHashCode

import java.time.LocalDate

@EqualsAndHashCode
class EmployeeDto extends AbstractEntityDto {

  long id
  String firstName
  String lastName
  String email
  String teamName
  boolean active
  LocalDate birthday
  String job
  String careerLevel

  String getName() {
    return firstName + " " + lastName
  }

  String getLevelAndJob() {
    return careerLevel + " " + job
  }
}
