package com.wagner.blueprint.service.impl;

import com.wagner.blueprint.model.entity.Employee;
import com.wagner.blueprint.model.repository.EmployeeRepository;
import com.wagner.blueprint.utils.EmployeeTestUtil;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class EmployeeServiceImplTest {

  private Employee employeeEntity1;
  private Employee employeeEntity2;
  private Employee employeeEntity3;

  private EmployeeRequestDto employeeRequestDto1;
  private EmployeeRequestDto employeeRequestDto2;

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    employeeEntity1     = EmployeeTestUtil.createDummyEntity();
    employeeEntity2     = EmployeeTestUtil.createDummyEntity();
    employeeEntity3     = EmployeeTestUtil.createDummyEntity();
    employeeRequestDto1 = EmployeeTestUtil.createDummyRequestDto();
    employeeRequestDto2 = EmployeeTestUtil.createDummyRequestDto();
  }

  @Test
  void test_findAll() {
    // given
    List<Employee> employees = Arrays.asList(employeeEntity2, employeeEntity1, employeeEntity3);
    when(employeeRepository.findAll()).thenReturn(employees);

    // when
    List<EmployeeDto> actualEmployeeList = employeeService.findAll();

    // then
    assertEquals(3, actualEmployeeList.size());
    verify(employeeRepository, times(1)).findAll();

    List<EmployeeDto> expectedEmployeeList = employees.stream()
            .map(entity -> new ModelMapper().map(entity, EmployeeDto.class))
            .collect(Collectors.toList());

    assertThatEmployeeDtosAreEqual(expectedEmployeeList, actualEmployeeList);
  }

  @ParameterizedTest
  @ValueSource(longs = {1,2})
  void test_find(long bla) {
    // given
    when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employeeEntity1));

    // when
    Optional<EmployeeDto> employeeDto = employeeService.find(bla);

    // then
    assertTrue(employeeDto.isPresent());
    assertThatDtoAndEntityAreEqual(employeeEntity1, employeeDto.get());
    verify(employeeRepository, times(1)).findById(anyLong());
  }

  @Test
  void test_find_return_empty_optional() {
    // given
    when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

    // when
    Optional<EmployeeDto> employeeDto = employeeService.find(1);

    // then
    assertFalse(employeeDto.isPresent());
    verify(employeeRepository, times(1)).findById(anyLong());
  }

  @Test
  void test_find_by_team_name() {
    // given
    List<Employee> employees = Arrays.asList(employeeEntity2, employeeEntity1);
    when(employeeRepository.findByTeamName(anyString())).thenReturn(employees);

    // when
    List<EmployeeDto> actualEmployeeList = employeeService.findByTeamName("");

    // then
    assertFalse(actualEmployeeList.isEmpty());
    verify(employeeRepository, times(1)).findByTeamName(anyString());

    List<EmployeeDto> expectedEmployeeList = employees.stream()
            .map(entity -> new ModelMapper().map(entity, EmployeeDto.class))
            .collect(Collectors.toList());

    assertThatEmployeeDtosAreEqual(expectedEmployeeList, actualEmployeeList);
  }

  @Test
  void test_create() {
    // given
    when(employeeRepository.save(any(Employee.class))).thenReturn(employeeEntity1);

    // when
    EmployeeDto employeeDto = employeeService.create(employeeRequestDto1);

    // then
    assertNotNull(employeeDto);
    assertThatRequestDtoAndDtoAreEqual(employeeRequestDto1, employeeDto);
    verify(employeeRepository, times(1)).save(any(Employee.class));
  }

  @Test
  void test_create_all() {
    // given
    when(employeeRepository.saveAll(any())).thenReturn(Arrays.asList(employeeEntity2, employeeEntity1));

    // when
    List<EmployeeRequestDto> employeeRequestDtos = Arrays.asList(employeeRequestDto1, employeeRequestDto2);
    List<EmployeeDto> employeeDto = employeeService.createAll(employeeRequestDtos);

    // then
    assertEquals(2, employeeDto.size());
    verify(employeeRepository, times(1)).saveAll(any());

    for (int index = 0; index < employeeRequestDtos.size(); index++) {
      assertThatRequestDtoAndDtoAreEqual(employeeRequestDtos.get(index), employeeDto.get(index));
    }
  }

  @Test
  void test_update() {
    // given
    when(employeeRepository.save(any(Employee.class))).thenReturn(employeeEntity1);
    when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity1));

    // when
    Optional<EmployeeDto> employeeDto = employeeService.update(1, employeeRequestDto1);

    // then
    assertNotNull(employeeDto);
    assertTrue(employeeDto.isPresent());
    assertThatRequestDtoAndDtoAreEqual(employeeRequestDto1, employeeDto.get());
  }

  @Test
  void test_update_not_existing_entity() {
    // given
    when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

    // when
    Optional<EmployeeDto> employeeDto = employeeService.update(1, employeeRequestDto1);

    // then
    assertNotNull(employeeDto);
    assertFalse(employeeDto.isPresent());
  }

  @Test
  void test_delete_existing() {
    // given
    when(employeeRepository.existsById(1L)).thenReturn(true);

    // when
    boolean actual = employeeService.delete(1L);

    // then
    assertTrue(actual);
  }

  @Test
  void test_delete_not_existing() {
    // given
    when(employeeRepository.existsById(1L)).thenReturn(false);

    // when
    boolean actual = employeeService.delete(1L);

    // then
    assertFalse(actual);
  }

  private void assertThatDtoAndEntityAreEqual(Employee entity, EmployeeDto dto) {
    assertEquals(entity.getFirstName(), dto.getFirstName());
    assertEquals(entity.getLastName(), dto.getLastName());
    assertEquals(entity.getBirthday(), dto.getBirthday());
    assertEquals(entity.getEmail(), dto.getEmail());
    assertEquals(entity.getTeamName(), dto.getTeamName());
    assertEquals(entity.getJob(), dto.getJob());
    assertEquals(entity.getCareerLevel(), dto.getCareerLevel());
    assertEquals(entity.isActive(), dto.isActive());
  }

  private void assertThatRequestDtoAndDtoAreEqual(EmployeeRequestDto requestDto, EmployeeDto dto) {
    assertEquals(requestDto.getFirstName(), dto.getFirstName());
    assertEquals(requestDto.getLastName(), dto.getLastName());
    assertEquals(requestDto.getBirthday(), dto.getBirthday());
    assertEquals(requestDto.getEmail(), dto.getEmail());
    assertEquals(requestDto.getTeamName(), dto.getTeamName());
    assertEquals(requestDto.getJob(), dto.getJob());
    assertEquals(requestDto.getCareerLevel(), dto.getCareerLevel());
    assertEquals(requestDto.isActive(), dto.isActive());
  }

  private void assertThatEmployeeDtosAreEqual(List<EmployeeDto> expected, List<EmployeeDto> actual) {
    for (int index = 0; index < expected.size(); index++) {
      EmployeeDto actualEmployee   = actual.get(index);
      EmployeeDto expectedEmployee = expected.get(index);

      assertEquals(expectedEmployee.getFirstName(), actualEmployee.getFirstName());
      assertEquals(expectedEmployee.getLastName(), actualEmployee.getLastName());
      assertEquals(expectedEmployee.getEmail(), actualEmployee.getEmail());
      assertEquals(expectedEmployee.getTeamName(), actualEmployee.getTeamName());
      assertEquals(expectedEmployee.getBirthday(), actualEmployee.getBirthday());
      assertEquals(expectedEmployee.getCareerLevel(), actualEmployee.getCareerLevel());
      assertEquals(expectedEmployee.getJob(), actualEmployee.getJob());
      assertEquals(expectedEmployee.isActive(), actualEmployee.isActive());
    }
  }

}
