package com.dammenhayn.blueprint.model.entity

import groovy.transform.EqualsAndHashCode

import javax.persistence.Column
import javax.persistence.Entity
import java.time.LocalDate

@Entity(name = "employees")
@EqualsAndHashCode(callSuper = true)
class EmployeeEntity extends AbstractEntity {

  @Column(nullable = false)
  String firstName

  @Column(nullable = false)
  String lastName

  @Column(unique = true, nullable = false)
  String email

  @Column(nullable = false)
  String team

  Boolean active
  LocalDate birthday
  String job
  String careerLevel
}
