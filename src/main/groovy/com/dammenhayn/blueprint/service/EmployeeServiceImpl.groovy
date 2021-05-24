package com.dammenhayn.blueprint.service

import com.dammenhayn.blueprint.model.entity.EmployeeEntity
import com.dammenhayn.blueprint.model.repository.EmployeeRepository
import com.dammenhayn.blueprint.web.dto.EmployeeDto
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository
  @Autowired
  ModelMapper modelMapper

  @Override
  List<EmployeeDto> findAll() {
    return employeeRepository.findAll().collect {
      modelMapper.map(it, EmployeeDto)
    }
  }

  @Override
  List<EmployeeDto> findAll(int currentPage, int itemsPerPage) {
    // PageRequest is index based, web frontend is not
    Pageable pageRequest = PageRequest.of(currentPage - 1, itemsPerPage)
    return employeeRepository.findAll(pageRequest).collect {
      modelMapper.map(it, EmployeeDto)
    }
  }

  @Override
  List<EmployeeDto> findByTeamName(String teamName) {
    return employeeRepository.findByTeamName(teamName).collect {
      modelMapper.map(it, EmployeeDto)
    }
  }

  @Override
  long getCount() {
    return employeeRepository.count()
  }

  @Override
  Optional<EmployeeDto> find(long id) {
    Optional<EmployeeEntity> certification = employeeRepository.findById(id)

    return certification.map(value -> modelMapper.map(value, EmployeeDto))
  }

  @Override
  EmployeeDto create(EmployeeRequestDto request) {
    EmployeeEntity employee = modelMapper.map(request, EmployeeEntity)
    employeeRepository.save(employee)

    return modelMapper.map(employee, EmployeeDto)
  }

  @Override
  List<EmployeeDto> createAll(Iterable<EmployeeRequestDto> request) {
    def employees = request.collect { modelMapper.map(it, EmployeeEntity) }

    employeeRepository.saveAll(employees)

    return employees.collect { modelMapper.map(it, EmployeeDto) }
  }

  @Override
  Optional<EmployeeDto> update(long id, EmployeeRequestDto request) {
    Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(id)
    EmployeeDto employeeResponse = null

    if (employeeOptional.isPresent()) {
      EmployeeEntity employee = employeeOptional.get()
      modelMapper.map(request, employee)

      employeeRepository.save(employee)
      employeeResponse = modelMapper.map(employee, EmployeeDto)
    }

    return Optional.ofNullable(employeeResponse)
  }

  @Override
  boolean delete(long id) {
    if (employeeRepository.existsById(id)) {
      employeeRepository.deleteById(id)
      return true
    }

    return false
  }
}
