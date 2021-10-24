package com.ffr.booklibrary.inventory.core.adapters.db;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventsJpaRepository extends CrudRepository<JpaEvent, UUID> {

    @Query("FROM JpaEvent e WHERE e.publishedDate is null")
    List<JpaEvent> findAllUnpublished();
}
