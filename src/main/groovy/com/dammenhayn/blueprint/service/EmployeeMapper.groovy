package com.dammenhayn.blueprint.service

import com.dammenhayn.blueprint.model.entity.EmployeeEntity
import com.dammenhayn.blueprint.web.dto.EmployeeDto
import com.dammenhayn.blueprint.web.dto.request.EmployeeRequestDto
import org.springframework.stereotype.Component

@Component
class EmployeeMapper {

  EmployeeDto map(EmployeeEntity entity) {
    if (entity) {
      return new EmployeeDto(firstName: entity.firstName,
                             lastName: entity.lastName,
                             email: entity.email,
                             team: entity.team,
                             birthday: entity.birthday,
                             job: entity.job,
                             careerLevel: entity.careerLevel,
                             active: entity.active)
    }
  }

  EmployeeEntity mapEntity(EmployeeRequestDto dto) {
    if (dto) {
      return new EmployeeEntity(firstName: dto.firstName,
                                lastName: dto.lastName,
                                email: dto.email,
                                team: dto.team,
                                birthday: dto.birthday,
                                job: dto.job,
                                careerLevel: dto.careerLevel,
                                active: dto.active)
    }
  }
}
