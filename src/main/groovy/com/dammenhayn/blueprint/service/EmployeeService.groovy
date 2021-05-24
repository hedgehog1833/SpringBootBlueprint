package com.dammenhayn.blueprint.service

import com.dammenhayn.blueprint.web.dto.EmployeeDto
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto

interface EmployeeService {

  List<EmployeeDto> findAll()

  List<EmployeeDto> findAll(int currentPage, int itemsPerPage)

  Optional<EmployeeDto> find(long id)

  EmployeeDto create(EmployeeRequestDto request)

  List<EmployeeDto> createAll(Iterable<EmployeeRequestDto> request)

  Optional<EmployeeDto> update(long id, EmployeeRequestDto request)

  boolean delete(long id)

  long getCount()

  List<EmployeeDto> findByTeamName(String teamName)

}
