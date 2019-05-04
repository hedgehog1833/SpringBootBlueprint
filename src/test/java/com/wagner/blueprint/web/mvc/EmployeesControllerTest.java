package com.wagner.blueprint.web.mvc;

import com.wagner.blueprint.config.Endpoints;
import com.wagner.blueprint.config.ViewNames;
import com.wagner.blueprint.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeesController.class)
class EmployeesControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EmployeeService employeeService;

  @Test
  void testShowEmployees() throws Exception {
    mockMvc.perform(get(Endpoints.EMPLOYEES_LIST))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name(ViewNames.EMPLOYESS))
            .andExpect(content().string(containsString("Employees")));
  }

  @Test
  void testShowEmployeesOnPage() throws Exception {
    mockMvc.perform(get(Endpoints.EMPLOYEES_LIST + "?page=2"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name(ViewNames.EMPLOYESS))
            .andExpect(model().attributeExists("employees", "pagination"))
            .andExpect(content().string(containsString("Employees")));
  }

}