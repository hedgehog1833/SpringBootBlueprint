package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeesController {

  @GetMapping(Endpoints.EMPLOYEES_LIST)
  public ModelAndView showEmployeesList() {
    return new ModelAndView(ViewNames.EMPLOYESS);
  }

}
