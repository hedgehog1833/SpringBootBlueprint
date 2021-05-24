package com.dammenhayn.blueprint.service

import com.dammenhayn.blueprint.model.entity.EmployeeEntity
import com.dammenhayn.blueprint.model.repository.EmployeeRepository
import com.dammenhayn.blueprint.web.dto.EmployeeDto
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository
  @Autowired
  EmployeeMapper employeeMapper

  @Override
  List<EmployeeDto> findAll() {
    return employeeRepository.findAll().collect {
      employeeMapper.map(it)
    }
  }

  @Override
  List<EmployeeDto> findAll(int currentPage, int itemsPerPage) {
    // PageRequest is index based, web frontend is not
    Pageable pageRequest = PageRequest.of(currentPage - 1, itemsPerPage)
    return employeeRepository.findAll(pageRequest).collect {
      employeeMapper.map(it)
    }
  }

  @Override
  List<EmployeeDto> findByTeamName(String teamName) {
    return employeeRepository.findByTeam(teamName).collect {
      employeeMapper.map(it)
    }
  }

  @Override
  long getCount() {
    return employeeRepository.count()
  }

  @Override
  Optional<EmployeeDto> find(long id) {
    Optional<EmployeeEntity> certification = employeeRepository.findById(id)
    return certification.map(EmployeeMapper::map)
  }

  @Override
  EmployeeDto create(EmployeeRequestDto request) {
    def employeeEntity = employeeMapper.mapEntity(request)
    def savedEntity = employeeRepository.save(employeeEntity)
    return employeeMapper.map(savedEntity)
  }

  @Override
  List<EmployeeDto> createAll(Iterable<EmployeeRequestDto> request) {
    def employeeEntities = request.collect { employeeMapper.mapEntity(it) }
    def savedEntities = employeeRepository.saveAll(employeeEntities)
    return savedEntities.collect { employeeMapper.map(it) }
  }

  @Override
  Optional<EmployeeDto> update(long id, EmployeeRequestDto request) {
    def employeeOptional = employeeRepository.findById(id)
    def employeeResponse = null

    if (employeeOptional.isPresent()) {
      def employee = employeeOptional.get()

      employee.firstName = request.firstName
      employee.lastName = request.lastName
      employee.email = request.email
      employee.team = request.team
      employee.birthday = request.birthday
      employee.active = request.active
      employee.job = request.job
      employee.careerLevel = request.careerLevel

      def savedEntity = employeeRepository.save(employee)
      employeeResponse = employeeMapper.map(savedEntity)
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
