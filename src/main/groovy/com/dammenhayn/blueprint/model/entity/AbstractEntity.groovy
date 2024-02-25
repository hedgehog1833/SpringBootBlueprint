package com.dammenhayn.blueprint.model.entity

import groovy.transform.EqualsAndHashCode
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Temporal
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener

import java.time.LocalDate
import java.time.ZoneId

import static jakarta.persistence.GenerationType.IDENTITY
import static jakarta.persistence.TemporalType.TIMESTAMP

@MappedSuperclass
@EntityListeners(AuditingEntityListener)
@EqualsAndHashCode(includes = "id")
abstract class AbstractEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  Long id

  @CreatedDate
  @Temporal(TIMESTAMP)
  Date createdDate

  @CreatedBy
  String createdBy

  @LastModifiedDate
  @Temporal(TIMESTAMP)
  Date lastModifiedDate

  @LastModifiedBy
  String lastModifiedBy

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
