package com.wagner.blueprint.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AbstractEntityDto {

  protected long      id;
  protected LocalDate createdDate;
  protected String    createdBy;
  protected LocalDate lastModifiedDate;
  protected String    lastModifiedBy;

}
