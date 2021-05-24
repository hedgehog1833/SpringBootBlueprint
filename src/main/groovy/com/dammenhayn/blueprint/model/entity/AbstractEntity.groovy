package com.dammenhayn.blueprint.model.entity

import groovy.transform.EqualsAndHashCode
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import javax.persistence.EntityListeners
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Temporal
import javax.persistence.TemporalType
import java.time.LocalDate
import java.time.ZoneId

@MappedSuperclass
@EntityListeners([AuditingEntityListener])
@EqualsAndHashCode
abstract class AbstractEntity {

  public static final long serialVersionUID = 1L

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate

  @CreatedBy
  private String createdBy

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate

  @LastModifiedBy
  private String lastModifiedBy

  long getId() {
    return id
  }

  LocalDate getCreatedDate() {
    return createdDate != null ? createdDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null
  }

  String getCreatedBy() {
    return createdBy
  }

  LocalDate getLastModifiedDate() {
    return lastModifiedDate != null ? lastModifiedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null
  }

  String getLastModifiedBy() {
    return lastModifiedBy
  }
}
