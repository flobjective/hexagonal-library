package com.ffr.booklibrary.core.inventory.adapters.db;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

@Repository
public interface BookJpaRepository extends CrudRepository<JpaBook, UUID> {

}
