package com.dammenhayn.blueprint.service.impl;

import com.dammenhayn.blueprint.model.entity.Employee;
import com.dammenhayn.blueprint.model.repository.EmployeeRepository;
import com.dammenhayn.blueprint.service.EmployeeService;
import com.dammenhayn.blueprint.web.dto.EmployeeDto;
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final ModelMapper        modelMapper;

  @Autowired
  public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
    this.modelMapper        = new ModelMapper();
  }

  @NotNull
  @Override
  public List<EmployeeDto> findAll() {
    return employeeRepository.findAll()
            .stream()
            .map(employee -> modelMapper.map(employee, EmployeeDto.class))
            .collect(Collectors.toList());
  }

  @NotNull
  @Override
  public List<EmployeeDto> findAll(int currentPage, int itemsPerPage) {
    // PageRequest is index based, web frontend is not
    Pageable pageRequest = PageRequest.of(currentPage - 1, itemsPerPage);
    return employeeRepository.findAll(pageRequest)
            .stream()
            .map(employee -> modelMapper.map(employee, EmployeeDto.class))
            .collect(Collectors.toList());
  }

  @Override
  public List<EmployeeDto> findByTeamName(String teamName) {
    return employeeRepository.findByTeamName(teamName)
            .stream()
            .map(employee -> modelMapper.map(employee, EmployeeDto.class))
            .collect(Collectors.toList());
  }

  @Override
  public long getCount() {
    return employeeRepository.count();
  }

  @NotNull
  @Override
  public Optional<EmployeeDto> find(long id) {
    Optional<Employee> certification = employeeRepository.findById(id);

    return certification.map(value -> modelMapper.map(value, EmployeeDto.class));
  }

  @NotNull
  @Override
  public EmployeeDto create(@NotNull EmployeeRequestDto request) {
    Employee employee = modelMapper.map(request, Employee.class);
    employeeRepository.save(employee);

    return modelMapper.map(employee, EmployeeDto.class);
  }

  @NotNull
  @Override
  public List<EmployeeDto> createAll(@NotNull Iterable<EmployeeRequestDto> request) {
    List<Employee> employees = new ArrayList<>();
    request.forEach(employeeRequestDto -> employees.add(modelMapper.map(employeeRequestDto, Employee.class)));

    employeeRepository.saveAll(employees);

    return employees.stream()
            .map(employee -> modelMapper.map(employee, EmployeeDto.class))
            .collect(Collectors.toList());
  }

  @NotNull
  @Override
  public Optional<EmployeeDto> update(long id, @NotNull EmployeeRequestDto request) {
    Optional<Employee> employeeOptional = employeeRepository.findById(id);
    EmployeeDto employeeResponse        = null;

    if (employeeOptional.isPresent()) {
      Employee employee = employeeOptional.get();
      modelMapper.map(request, employee);

      employeeRepository.save(employee);
      employeeResponse = modelMapper.map(employee, EmployeeDto.class);
    }

    return Optional.ofNullable(employeeResponse);
  }

  @Override
  public boolean delete(long id) {
    if (employeeRepository.existsById(id)) {
      employeeRepository.deleteById(id);
      return true;
    }

    // entity doesn't exist
    return false;
  }
}
