package com.dammenhayn.blueprint.utils;

import com.dammenhayn.blueprint.model.entity.Employee;
import com.dammenhayn.blueprint.web.dto.EmployeeDto;
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto;

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
    employee.setTeamName(RandomGenerator.randomString(10));
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
            .teamName(RandomGenerator.randomString(10))
            .birthday(RandomGenerator.randomLocalDate())
            .active(RandomGenerator.randomBoolean())
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
    dto.setEmail(RandomGenerator.randomEmail());
    dto.setTeamName(RandomGenerator.randomString(10));
    dto.setBirthday(RandomGenerator.randomLocalDate());
    dto.setActive(RandomGenerator.randomBoolean());
    dto.setJob(RandomGenerator.randomString(5));
    dto.setCareerLevel(RandomGenerator.randomString(10));

    return dto;
  }

}
