package com.dammenhayn.blueprint.model.repository

import com.dammenhayn.blueprint.config.DatabaseConfiguration
import com.dammenhayn.blueprint.model.entity.EmployeeEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@DataJpaTest
@ContextConfiguration(classes = [DatabaseConfiguration])
@TestPropertySource(locations = "classpath:integrationtest.properties")
class EmployeeRepositoryIT extends Specification {

  static EmployeeEntity EMPLOYEE_1 = new EmployeeEntity(id: 1, firstName: "Napoleon", lastName: "Dynamite", email: "napoleon@test.com", team: "TeamPedro")
  static EmployeeEntity EMPLOYEE_2 = new EmployeeEntity(id: 2, firstName: "Pedro", lastName: "Sanchez", email: "pedro@test.com",team: "TeamPedro")
  static EmployeeEntity EMPLOYEE_3 = new EmployeeEntity(id: 3, firstName: "Uncle", lastName: "Rico", email: "rico@test.com",team: "TeamRico")

  @Autowired
  EmployeeRepository underTest

  void setup() {
    def employees = [EMPLOYEE_1, EMPLOYEE_2, EMPLOYEE_3]
    underTest.saveAll(employees)
  }

  void cleanup() {
    underTest.deleteAll()
  }

  def "findAllByTeam returns employees with given team"() {
    given:
    def team = "TeamPedro"

    when:
    def result = underTest.findByTeam(team)

    then:
    result == [EMPLOYEE_1, EMPLOYEE_2]
  }

  def "findAllByTeam returns empty list when no entities are found"() {
    when:
    def result = underTest.findByTeam("unknownTeam")

    then:
    !result
  }
}
