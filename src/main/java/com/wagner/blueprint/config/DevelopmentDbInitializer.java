package com.wagner.blueprint.config;

import com.wagner.blueprint.service.EmployeeService;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@Profile({"dev", "integration-test"})
public class DevelopmentDbInitializer implements ApplicationRunner {

  private final EmployeeService employeeService;

  @Autowired
  public DevelopmentDbInitializer(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Override
  public void run(ApplicationArguments applicationArguments) {
    createTestEmployees();
  }

  private void createTestEmployees() {
    EmployeeRequestDto employee1 = EmployeeRequestDto.builder()
            .firstName("John")
            .lastName("Doe")
            .email("john.doe@example.com")
            .birthday(LocalDate.of(1970, 1, 1))
            .isActive(true)
            .job("Java Developer")
            .careerLevel("Senior")
            .build();

    EmployeeRequestDto employee2 = EmployeeRequestDto.builder()
            .firstName("Owen")
            .lastName("Hunt")
            .email("owen.hunt@example.com")
            .birthday(LocalDate.of(1992, 10, 26))
            .isActive(true)
            .job("Java Developer")
            .careerLevel("Junior")
            .build();

    EmployeeRequestDto employee3 = EmployeeRequestDto.builder()
            .firstName("Elisa")
            .lastName("Baily")
            .email("elisa.baily@example.com")
            .birthday(LocalDate.of(1965, 7, 5))
            .isActive(true)
            .job("Product Manager")
            .careerLevel("Experienced Senior")
            .build();

    EmployeeRequestDto employee4 = EmployeeRequestDto.builder()
            .firstName("Tom")
            .lastName("Hart")
            .email("tom.hart@example.com")
            .birthday(LocalDate.of(1987, 9, 15))
            .isActive(false)
            .job("QA Engineer")
            .careerLevel("Professional")
            .build();

    List<EmployeeRequestDto> employeeDtos = Arrays.asList(employee1, employee2, employee3, employee4);
    employeeService.createAll(employeeDtos);
  }

}
