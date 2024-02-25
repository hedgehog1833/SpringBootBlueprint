package com.dammenhayn.blueprint.config

class Endpoints {

  public static final String INDEX              = "/index"
  public static final String EMPTY_INDEX        = ""
  public static final String SLASH_INDEX        = "/"

  public static final String EMPLOYEES_LIST     = "/employees"
  public static final String EMPLOYEES_CREATE   = "/employees/create"
  public static final String EMPLOYEES_EDIT     = "/employees/edit/{id}"
  public static final String EMPLOYEES_DELETE   = "/employees/delete/{id}"

  public static final String ERROR              = "/error"

  public static final String ACTUATOR_ENDPOINTS = "/actuator/**"

  static class REST {
    public static final String EMPLOYEES_REST_V1 = "/rest/v1/employees"
  }
}
