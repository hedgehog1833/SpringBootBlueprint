package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import com.wagner.blueprint.service.EmployeeService;
import com.wagner.blueprint.web.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmployeesController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeesController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping(Endpoints.EMPLOYEES_LIST)
  public ModelAndView showEmployeesList() {
    List<EmployeeDto> employeeDtos = employeeService.findAll();
    return new ModelAndView(ViewNames.EMPLOYESS, "employees", employeeDtos);
  }

}
