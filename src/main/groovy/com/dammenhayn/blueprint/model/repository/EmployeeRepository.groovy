package com.dammenhayn.blueprint.model.repository

import com.dammenhayn.blueprint.model.entity.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

  List<EmployeeEntity> findByTeamName(String teamName)
}
