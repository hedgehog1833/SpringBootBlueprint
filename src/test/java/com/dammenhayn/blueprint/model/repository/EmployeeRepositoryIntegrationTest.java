package com.dammenhayn.blueprint.model.repository;

import com.dammenhayn.blueprint.model.entity.Employee;
import com.dammenhayn.blueprint.utils.EmployeeTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class EmployeeRepositoryIntegrationTest {

  private final Employee employee = EmployeeTestUtil.createDummyEntity();

  @Autowired private DataSource         dataSource;
  @Autowired private JdbcTemplate       jdbcTemplate;
  @Autowired private EntityManager      entityManager;
  @Autowired private EmployeeRepository employeeRepository;

  @Test
  void test_injected_components_are_not_null(){
    assertThat(dataSource).isNotNull();
    assertThat(jdbcTemplate).isNotNull();
    assertThat(entityManager).isNotNull();
    assertThat(employeeRepository).isNotNull();
  }

  @Test
  void test_find_employee() {
    entityManager.persist(employee);
    Optional<Employee> result = employeeRepository.findById(1L);
    assertThat(result.isPresent()).isTrue();

    //noinspection OptionalGetWithoutIsPresent
    Employee employeeResponse = result.get();

    assertThat(employeeResponse).isNotNull();
    assertThat(employeeResponse.getId()).isEqualTo(1L);
    assertThat(employeeResponse.getFirstName()).isEqualTo(employee.getFirstName());
    assertThat(employeeResponse.getLastName()).isEqualTo(employee.getLastName());
    assertThat(employeeResponse.getEmail()).isEqualTo(employee.getEmail());
    assertThat(employeeResponse.getTeamName()).isEqualTo(employee.getTeamName());
    assertThat(employeeResponse.getBirthday()).isEqualTo(employee.getBirthday());
    assertThat(employeeResponse.getJob()).isEqualTo(employee.getJob());
    assertThat(employeeResponse.getCareerLevel()).isEqualTo(employee.getCareerLevel());
    assertThat(employeeResponse.isActive()).isEqualTo(employee.isActive());
  }

}
