package com.dammenhayn.blueprint.framework.service;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for CRUD-Operations.
 * @param <T> DTO for Request
 * @param <U> DTO for response
 */
public interface SimpleCrudService<T, U> {

  @NotNull
  List<U> findAll();

  @NotNull
  List<U> findAll(int currentPage, int itemsPerPage);

  @NotNull
  Optional<U> find(long id);

  @NotNull
  U create(@NotNull T request);

  @NotNull
  List<U> createAll(@NotNull Iterable<T> request);

  @NotNull
  Optional<U> update(long id, @NotNull T request);

  boolean delete(long id);

}
