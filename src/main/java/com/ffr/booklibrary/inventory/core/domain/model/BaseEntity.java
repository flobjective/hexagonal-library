package com.ffr.booklibrary.inventory.core.domain.model;

import java.util.Objects;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@Accessors(fluent = true)
public abstract class BaseEntity {

  private UUID id;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseEntity that = (BaseEntity) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
