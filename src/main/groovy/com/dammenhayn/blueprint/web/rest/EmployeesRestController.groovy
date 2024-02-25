package com.dammenhayn.blueprint.web.rest

import com.dammenhayn.blueprint.config.Endpoints
import com.dammenhayn.blueprint.model.exception.RestRequestValidationException
import com.dammenhayn.blueprint.service.EmployeeService
import com.dammenhayn.blueprint.web.dto.EmployeeDto
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import static com.dammenhayn.blueprint.model.exception.ErrorMessages.VALIDATION_ERROR
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@RestController
@RequestMapping(Endpoints.REST.EMPLOYEES_REST_V1)
class EmployeesRestController {

  @Autowired
  EmployeeService employeeService

  @GetMapping(path = "/{id}", produces = APPLICATION_JSON_VALUE)
  ResponseEntity<EmployeeDto> get(@PathVariable long id) {
    Optional<EmployeeDto> certificationDto = employeeService.find(id)

    return ResponseEntity.of(certificationDto)
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  ResponseEntity<List<EmployeeDto>> getAll() {
    List<EmployeeDto> employeeDtos = employeeService.findAll()

    return ResponseEntity.ok(employeeDtos)
  }

  @GetMapping(path = "/search", produces = APPLICATION_JSON_VALUE)
  ResponseEntity<List<EmployeeDto>> getAllByTeam(@RequestParam(name = "teamName", defaultValue = "") String teamName) {
    if (teamName.isEmpty()) {
      return getAll()
    }

    List<EmployeeDto> employeeDtos = employeeService.findByTeamName(teamName)
    return ResponseEntity.ok(employeeDtos)
  }

  @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeRequestDto request, BindingResult bindingResult) {
    validateRequest(bindingResult)

    EmployeeDto certification = employeeService.create(request)
    return ResponseEntity.status(HttpStatus.CREATED).body(certification)
  }

  @PutMapping(path = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  ResponseEntity<EmployeeDto> update(@PathVariable long id,
                                     @Valid @RequestBody EmployeeRequestDto request,
                                     BindingResult bindingResult) {
    validateRequest(bindingResult)

    Optional<EmployeeDto> certification = employeeService.update(id, request)
    return ResponseEntity.of(certification)
  }

  @DeleteMapping(path = "/{id}")
  ResponseEntity<Void> delete(@PathVariable long id) {
    boolean success = employeeService.delete(id)

    return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build() as ResponseEntity<Void>
  }

  private void validateRequest(BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      throw new RestRequestValidationException(VALIDATION_ERROR.toDisplayString(), bindingResult.getFieldErrors())
    }
  }
}
