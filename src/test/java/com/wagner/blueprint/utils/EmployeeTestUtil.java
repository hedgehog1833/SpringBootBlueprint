package com.wagner.blueprint.utils;

import com.wagner.blueprint.model.entity.Employee;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;

public class EmployeeTestUtil {

  private EmployeeTestUtil() {
    // has only static methods
  }

  /*--------------------Employee---------------------*/
  public static Employee createDummyEntity() {
    Employee employee = new Employee();
    employee.setFirstName(RandomGenerator.randomFirstName());
    employee.setLastName(RandomGenerator.randomLastName());
    employee.setEmail(RandomGenerator.randomEmail());
    employee.setBirthday(RandomGenerator.randomLocalDate());
    employee.setActive(RandomGenerator.randomBoolean());
    employee.setJob(RandomGenerator.randomString(5));
    employee.setCareerLevel(RandomGenerator.randomString(10));

    return employee;
  }

  /*----------------EmployeeRequestDto---------------*/
  public static EmployeeRequestDto createDummyRequestDto() {
    return EmployeeRequestDto.builder()
            .firstName(RandomGenerator.randomFirstName())
            .lastName(RandomGenerator.randomLastName())
            .email(RandomGenerator.randomEmail())
            .birthday(RandomGenerator.randomLocalDate())
            .isActive(RandomGenerator.randomBoolean())
            .job(RandomGenerator.randomString(5))
            .careerLevel(RandomGenerator.randomString(10))
            .build();
  }

  /*-------------------EmployeeDto-------------------*/
  public static EmployeeDto createDummyDto() {
    EmployeeDto dto = new EmployeeDto();
    dto.setId(1);
    dto.setFirstName(RandomGenerator.randomFirstName());
    dto.setLastName(RandomGenerator.randomLastName());
    dto.setEmail(RandomGenerator.randomLastName());
    dto.setBirthday(RandomGenerator.randomLocalDate());
    dto.setActive(RandomGenerator.randomBoolean());
    dto.setJob(RandomGenerator.randomString(5));
    dto.setCareerLevel(RandomGenerator.randomString(10));

    return dto;
  }

}
