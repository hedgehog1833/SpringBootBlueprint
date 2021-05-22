package com.dammenhayn.blueprint.model.repository;

import com.dammenhayn.blueprint.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  List<Employee> findByTeamName(String teamName);
}
