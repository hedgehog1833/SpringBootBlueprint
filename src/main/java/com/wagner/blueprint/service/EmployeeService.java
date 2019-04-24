package com.wagner.blueprint.service;

import com.wagner.blueprint.framework.service.SimpleCrudService;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;

public interface EmployeeService extends SimpleCrudService<EmployeeRequestDto, EmployeeDto> {
}
