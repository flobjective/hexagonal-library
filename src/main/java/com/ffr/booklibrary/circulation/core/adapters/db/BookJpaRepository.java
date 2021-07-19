package com.ffr.booklibrary.circulation.core.adapters.db;

import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookJpaRepository extends CrudRepository<JpaBook, UUID> {
    @Query("FROM JpaBook b WHERE b.bookId = :id")
    Optional<JpaBook> findByBookId(UUID id);

    @Query("FROM JpaBook b WHERE b.currentIssue is not null AND b.currentIssue.userId = :id")
    List<JpaBook> findIssuedByUserId(UUID id);

    @Query("FROM JpaBook b WHERE b.currentIssue is null")
    List<JpaBook> findAvailable();

    @Query("FROM JpaBook b WHERE b.currentIssue is not null")
    List<JpaBook> findIssued();
}
