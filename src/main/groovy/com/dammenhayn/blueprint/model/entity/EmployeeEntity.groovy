package com.dammenhayn.blueprint.model.entity

import javax.persistence.Column
import javax.persistence.Entity
import java.time.LocalDate

@Entity(name = "employees")
class EmployeeEntity extends AbstractEntity {

  public static final long serialVersionUID = 1L

  @Column(nullable = false)
  String firstName

  @Column(nullable = false)
  String lastName

  @Column(unique = true, nullable = false)
  String email

  @Column(nullable = false)
  String teamName

  boolean active
  LocalDate birthday
  String job
  String careerLevel

}
