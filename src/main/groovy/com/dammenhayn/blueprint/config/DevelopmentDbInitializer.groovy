package com.dammenhayn.blueprint.config

import com.dammenhayn.blueprint.service.EmployeeService
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

import java.time.LocalDate

@Component
class DevelopmentDbInitializer implements ApplicationRunner {

  @Autowired
  EmployeeService employeeService

  @Override
  void run(ApplicationArguments applicationArguments) {
    createTestEmployees()
  }

  private void createTestEmployees() {
    def employee1 = new EmployeeRequestDto(firstName: "John",
                                           lastName: "Doe",
                                           email: "john.doe@example.com",
                                           team: "Frontend",
                                           birthday: LocalDate.of(1970, 1, 1),
                                           active: true,
                                           job: "Java Developer",
                                           careerLevel: "Senior")

    def employee2 = new EmployeeRequestDto(firstName: "Owen",
                                           lastName: "Hunt",
                                           email: "owen.hunt@example.com",
                                           team: "Backend",
                                           birthday: LocalDate.of(1992, 10, 26),
                                           active: true,
                                           job: "Java Developer",
                                           careerLevel: "Junior")

    def employee3 = new EmployeeRequestDto(firstName: "Elisa",
                                           lastName: "Baily",
                                           email: "elisa.baily@example.com",
                                           team: "TeamPM",
                                           birthday: LocalDate.of(1965, 7, 5),
                                           active: true,
                                           job: "Product Manager",
                                           careerLevel: "Experienced Senior")

    def employee4 = new EmployeeRequestDto(firstName: "Tom",
                                           lastName: "Hart",
                                           email: "tom.hart@example.com",
                                           team: "TeamQA",
                                           birthday: LocalDate.of(1987, 9, 15),
                                           active: false,
                                           job: "QA Engineer",
                                           careerLevel: "Professional")

    def employee5 = new EmployeeRequestDto(firstName: "Miranda",
                                           lastName: "Grey",
                                           email: "miranda.grey@example.com",
                                           team: "Backend",
                                           birthday: LocalDate.of(1982, 1, 15),
                                           active: false,
                                           job: "Java Developer",
                                           careerLevel: "Senior")

    def employeeDtos = [employee1, employee2, employee3, employee4, employee5]
    employeeService.createAll(employeeDtos)
  }
}
