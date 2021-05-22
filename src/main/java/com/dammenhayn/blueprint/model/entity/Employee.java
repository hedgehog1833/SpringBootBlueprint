package com.dammenhayn.blueprint.model.entity;

import com.dammenhayn.blueprint.framework.jpa.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "Employee")
@Table(name = "employee")
public class Employee extends AbstractEntity {

  public static final long serialVersionUID = 1L;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String teamName;

  private boolean active;
  private LocalDate birthday;
  private String job;
  private String careerLevel;

}
