package com.dammenhayn.blueprint.framework.jpa.entity;

import java.time.LocalDate;

/**
 * Interface for entity objects.
 */
public interface Entity {

  long getId();

  LocalDate getCreatedDate();

  String getCreatedBy();

  LocalDate getLastModifiedDate();

  String getLastModifiedBy();

}
