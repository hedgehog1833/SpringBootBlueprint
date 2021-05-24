package com.dammenhayn.blueprint.web.dto

import java.time.LocalDate

class AbstractEntityDto {

  protected long id
  protected LocalDate createdDate
  protected String createdBy
  protected LocalDate lastModifiedDate
  protected String lastModifiedBy
}
