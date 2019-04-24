package com.wagner.blueprint.web.controller;

import com.wagner.blueprint.framework.controller.SimpleCrudRestController;
import com.wagner.blueprint.service.EmployeeService;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/v1/employees")
public class EmployeesRestController implements SimpleCrudRestController<EmployeeRequestDto, EmployeeDto> {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeesRestController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Override
  @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<EmployeeDto> get(@PathVariable long id) {
    Optional<EmployeeDto> certificationDto = employeeService.find(id);

    return ResponseEntity.of(certificationDto);
  }

  @Override
  @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<EmployeeDto>> getAll() {
    List<EmployeeDto> employeeDtos = employeeService.findAll();

    return ResponseEntity.ok(employeeDtos);
  }

  @Override
  @PostMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
               consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<EmployeeDto> create(@Valid @RequestBody EmployeeRequestDto request, BindingResult bindingResult) {
    validateRequest(bindingResult);

    EmployeeDto certification = employeeService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(certification);
  }

  @Override
  @PutMapping(path = "/{id}", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
                            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<EmployeeDto> update(@PathVariable long id,
                                            @Valid @RequestBody EmployeeRequestDto request,
                                            BindingResult bindingResult) {
    validateRequest(bindingResult);

    Optional<EmployeeDto> certification = employeeService.update(id, request);
    return ResponseEntity.of(certification);
  }

  @Override
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable long id) {
    boolean success = employeeService.delete(id);

    return success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
  }
}
