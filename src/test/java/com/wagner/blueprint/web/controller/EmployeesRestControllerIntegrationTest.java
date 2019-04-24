package com.wagner.blueprint.web.controller;

import com.wagner.blueprint.service.EmployeeService;
import com.wagner.blueprint.utils.EmployeeTestUtil;
import com.wagner.blueprint.utils.RestAssuredRequestHandler;
import com.wagner.blueprint.web.dto.EmployeeDto;
import com.wagner.blueprint.web.dto.ErrorResponse;
import com.wagner.blueprint.web.dto.request.EmployeeRequestDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class EmployeesRestControllerIntegrationTest {

  private static final String RESOURCE_PATH              = "/rest/v1/employees";
  private static final String RESOURCE_ID                = "1";
  private static final String NOT_EXISTING_RESOURCE_ID   = "99999";

  @Value("${server.address}")
  private String serverAddress;

  @LocalServerPort
  private int port;

  private EmployeeDto employeeDto;
  private EmployeeRequestDto employeeRequestDto;

  @Autowired
  private EmployeeService employeeService;

  private RestAssuredRequestHandler requestHandler;

  @BeforeEach
  void setUp() {
    String requestUri  = "http://" + serverAddress + ":" + port + RESOURCE_PATH;
    requestHandler     = new RestAssuredRequestHandler(requestUri);
    employeeDto        = EmployeeTestUtil.createDummyDto();
    employeeRequestDto = EmployeeTestUtil.createDummyRequestDto();
  }

  @Test
  void test_get() {
    ValidatableResponse response = requestHandler.doGet(ContentType.JSON, RESOURCE_ID);

    // assert
    response.contentType(ContentType.JSON)
            .statusCode(HttpStatus.OK.value());

    EmployeeDto responseDto = response.extract().as(EmployeeDto.class);

    assertNotNull(responseDto);
    assertEquals(1, responseDto.getId());
    assertEquals("John", responseDto.getFirstName());
    assertEquals("Doe", responseDto.getLastName());
    assertEquals("john.doe@example.com", responseDto.getEmail());
    assertEquals(LocalDate.of(1970, 1, 1), responseDto.getBirthday());
    assertTrue(responseDto.isActive());
    assertEquals("Java Developer", responseDto.getJob());
    assertEquals("Senior", responseDto.getCareerLevel());
  }

  @Test
  void get_on_not_existing_resource_should_return_404() {
    ValidatableResponse validatableResponse = requestHandler.doGet(ContentType.JSON, NOT_EXISTING_RESOURCE_ID);

    // assert
    validatableResponse.statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  void getAll() {
    ValidatableResponse validatableResponse = requestHandler.doGetAll(ContentType.JSON);

    // assert
    validatableResponse.contentType(ContentType.JSON)
                       .statusCode(HttpStatus.OK.value());

    Response response = validatableResponse.extract().response();

    assertNotNull(response);
    assertNotNull(response.getBody());

    EmployeeDto[] employeeDtos = response.getBody().as(EmployeeDto[].class);
    assertEquals(4, employeeDtos.length);

    // assert that all fields have a value
    Stream.of(employeeDtos).forEach(employeeDto -> {
      assertTrue(employeeDto.getId() != 0);
      assertNotNull(employeeDto.getFirstName());
      assertNotNull(employeeDto.getLastName());
      assertNotNull(employeeDto.getEmail());
      assertNotNull(employeeDto.getBirthday());
      assertNotNull(employeeDto.getJob());
      assertNotNull(employeeDto.getCareerLevel());
      assertNotNull(employeeDto.getCreatedDate());
      assertNotNull(employeeDto.getCreatedBy());
      assertNotNull(employeeDto.getLastModifiedDate());
      assertNotNull(employeeDto.getLastModifiedBy());
    });
  }

  @Test
  void create_with_valid_request_should_return_201() {
    Map<String, Object> request             = employeeRequestDto.toMap();
    ValidatableResponse validatableResponse = requestHandler.doPost(ContentType.JSON, request);
    EmployeeDto         createdEmployee     = validatableResponse.extract().as(EmployeeDto.class);

    // assert
    validatableResponse.statusCode(HttpStatus.CREATED.value())
                       .contentType(ContentType.JSON);

    assertNotNull(createdEmployee);
    assertTrue(createdEmployee.getId() != 0);
    assertEquals(employeeRequestDto.getFirstName(), createdEmployee.getFirstName());
    assertEquals(employeeRequestDto.getLastName(), createdEmployee.getLastName());
    assertEquals(employeeRequestDto.getEmail(), createdEmployee.getEmail());
    assertEquals(employeeRequestDto.getBirthday(), createdEmployee.getBirthday());
    assertEquals(employeeRequestDto.isActive(), createdEmployee.isActive());
    assertEquals(employeeRequestDto.getJob(), createdEmployee.getJob());
    assertEquals(employeeRequestDto.getCareerLevel(), createdEmployee.getCareerLevel());
    assertEquals("Administrator", createdEmployee.getCreatedBy());
    assertEquals(LocalDate.now(), createdEmployee.getCreatedDate());
    assertEquals("Administrator", createdEmployee.getLastModifiedBy());
    assertEquals(LocalDate.now(), createdEmployee.getLastModifiedDate());

    // remove created employee
    employeeService.delete(createdEmployee.getId());
  }

  @Test
  void create_with_invalid_request_should_return_400() {
    Map<String, String> request = new HashMap<>();
    request.put("firstName", null);
    request.put("lastName", " ");
    request.put("email", "valid-email@mail.de");

    ValidatableResponse validatableResponse = requestHandler.doPost(ContentType.JSON, request);
    ErrorResponse       errorResponse       = validatableResponse.extract().as(ErrorResponse.class);

    // assert
    validatableResponse.statusCode(HttpStatus.BAD_REQUEST.value())
                       .contentType(ContentType.JSON);

    assertNotNull(errorResponse);
    assertEquals(3, errorResponse.getMessages().size());
  }

  @Test
  void update_an_existing_resource_should_return_200() {
    // create a new employee which can be updated
    EmployeeDto testEmployee = employeeService.create(EmployeeTestUtil.createDummyRequestDto());

    Map<String, Object> request             = employeeRequestDto.toMap();
    String              resourceId          = Long.toString(testEmployee.getId());
    ValidatableResponse validatableResponse = requestHandler.doPut(ContentType.JSON, request, resourceId);
    EmployeeDto         updatedEmployeeDto  = validatableResponse.extract().as(EmployeeDto.class);

    // assert
    validatableResponse.statusCode(HttpStatus.OK.value())
                       .contentType(ContentType.JSON);

    assertNotNull(updatedEmployeeDto);
    assertEquals(employeeRequestDto.getFirstName(), updatedEmployeeDto.getFirstName());
    assertEquals(employeeRequestDto.getLastName(), updatedEmployeeDto.getLastName());
    assertEquals(employeeRequestDto.getEmail(), updatedEmployeeDto.getEmail());
    assertEquals(employeeRequestDto.getBirthday(), updatedEmployeeDto.getBirthday());
    assertEquals(employeeRequestDto.isActive(), updatedEmployeeDto.isActive());
    assertEquals(employeeRequestDto.getJob(), updatedEmployeeDto.getJob());
    assertEquals(employeeRequestDto.getCareerLevel(), updatedEmployeeDto.getCareerLevel());

    // id should not be updated
    assertEquals(testEmployee.getId(), updatedEmployeeDto.getId());

    // remove created employee
    employeeService.delete(testEmployee.getId());
  }

  @Test
  void update_a_not_existing_resource_should_return_404() {
    ValidatableResponse validatableResponse = requestHandler.doPut(ContentType.JSON, employeeRequestDto.toMap(), NOT_EXISTING_RESOURCE_ID);

    // assert
    validatableResponse.statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  void update_with_invalid_request_should_return_400() {
    Map<String, String> request = new HashMap<>();
    request.put("firstName", "");
    request.put("lastName", null);
    request.put("email", "invalid-email");

    ValidatableResponse validatableResponse = requestHandler.doPut(ContentType.JSON, request, RESOURCE_ID);
    ErrorResponse       errorResponse       = validatableResponse.extract().as(ErrorResponse.class);

    // assert
    validatableResponse.statusCode(HttpStatus.BAD_REQUEST.value())
                       .contentType(ContentType.JSON);

    assertNotNull(errorResponse);
    assertEquals(4, errorResponse.getMessages().size());
  }

  @Test
  void delete_an_existing_resource_should_return_200() {
    // create a new employee which can be deleted
    EmployeeDto employeeDto = employeeService.create(employeeRequestDto);

    ValidatableResponse validatableResponse = requestHandler.doDelete(Long.toString(employeeDto.getId()));

    // assert
    validatableResponse.statusCode(HttpStatus.OK.value());
    assertFalse(employeeService.find(employeeDto.getId()).isPresent());
  }

  @Test
  void delete_on_not_existing_resource_should_return_404() {
    ValidatableResponse validatableResponse = requestHandler.doDelete(NOT_EXISTING_RESOURCE_ID);

    // assert
    validatableResponse.statusCode(HttpStatus.NOT_FOUND.value());
  }

}