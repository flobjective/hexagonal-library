package com.ffr.booklibrary.core.circulation.adapters.db;

import com.ffr.booklibrary.core.circulation.core.model.UserId;
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

    @Query("FROM JpaBook b WHERE b.currentIssue.userId = :userId")
    List<JpaBook> findIssuedByUserId(UserId userId);

    @Query("FROM JpaBook b")
    List<JpaBook> findAvailable();

    @Query("FROM JpaBook b WHERE b.currentIssue is not null")
    List<JpaBook> findIssued();
}
