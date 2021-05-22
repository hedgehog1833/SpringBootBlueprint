package com.dammenhayn.blueprint.service;

import com.dammenhayn.blueprint.framework.service.SimpleCrudService;
import com.dammenhayn.blueprint.web.dto.EmployeeDto;
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto;

import java.util.List;

public interface EmployeeService extends SimpleCrudService<EmployeeRequestDto, EmployeeDto> {

  long getCount();

  List<EmployeeDto> findByTeamName(String teamName);

}
