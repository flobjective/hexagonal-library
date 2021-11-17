package com.ffr.booklibrary.circulation.core.adapters.db;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.UUID;

@Repository
public interface UserJpaRepository extends CrudRepository<JpaUser, UUID> {
}
