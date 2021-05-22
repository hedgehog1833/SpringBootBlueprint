package com.dammenhayn.blueprint.config;

public class Endpoints {

  // General
  public static final String INDEX              = "/index";
  public static final String EMPTY_INDEX        = "";
  public static final String SLASH_INDEX        = "/";

  // Employees
  public static final String EMPLOYEES_LIST     = "/employees";
  public static final String EMPLOYEES_CREATE   = "/employees/create";
  public static final String EMPLOYEES_EDIT     = "/employees/edit/{id}";
  public static final String EMPLOYEES_DELETE   = "/employees/delete/{id}";

  // Errors
  public static final String ERROR              = "/error";

  public static class REST {
    public static final String EMPLOYEES_REST_V1 = "/rest/v1/employees";
  }

}
