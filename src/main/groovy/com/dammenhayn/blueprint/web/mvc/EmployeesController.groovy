package com.dammenhayn.blueprint.web.mvc

import com.dammenhayn.blueprint.config.Endpoints
import com.dammenhayn.blueprint.config.ViewNames
import com.dammenhayn.blueprint.service.EmployeeService
import com.dammenhayn.blueprint.web.dto.EmployeeDto
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto
import com.dammenhayn.blueprint.web.dto.response.PaginationResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

import javax.validation.Valid

@Controller
class EmployeesController {

  private static final int ITEMS_PER_PAGE = 2

  @Autowired
  EmployeeService employeeService

  @GetMapping(Endpoints.EMPLOYEES_LIST)
  ModelAndView showEmployeesList(@RequestParam(name = "page", defaultValue = "1") int page) {
    List<EmployeeDto> employeeDtos = employeeService.findAll(page, ITEMS_PER_PAGE)

    Map<String, Object> viewModel =
        [employees : employeeDtos,
         createUrl : ViewNames.EMPLOYESS_CREATE,
         pagination: createPaginationResponse(page)]

    return new ModelAndView(ViewNames.EMPLOYESS_LIST, viewModel)
  }

  @GetMapping(Endpoints.EMPLOYEES_CREATE)
  ModelAndView showCreateEmployeeForm() {
    Map<String, Object> viewModel = [createUrl: ViewNames.EMPLOYESS_CREATE]

    return new ModelAndView(ViewNames.EMPLOYESS_CREATE, viewModel)
  }

  @PostMapping(Endpoints.EMPLOYEES_CREATE)
  ModelAndView createEmployee(@Valid EmployeeRequestDto employeeRequestDto, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      // show create form again if there are any errors
      return new ModelAndView(ViewNames.EMPLOYESS_CREATE)
    }

    employeeService.create(employeeRequestDto)

    return new ModelAndView("redirect:" + Endpoints.EMPLOYEES_LIST)
  }

  @GetMapping(Endpoints.EMPLOYEES_EDIT)
  ModelAndView showEditEmployeeForm(@PathVariable(name = "id") long id) {
    return new ModelAndView(ViewNames.EMPLOYESS_EDIT)
  }

  @ModelAttribute(name = "employeeRequestDto")
  EmployeeRequestDto employeeRequest() {
    return new EmployeeRequestDto()
  }

  private PaginationResponseDto createPaginationResponse(int currentPage) {
    long employeeCount = employeeService.getCount()
    int numberOfPages = (int) Math.ceil((double) employeeCount / (double) ITEMS_PER_PAGE)

    return new PaginationResponseDto(numberOfPages: numberOfPages,
                                     currentPage: currentPage,
                                     itemsPerPage: ITEMS_PER_PAGE)
  }
}
