package com.ffr.booklibrary.circulation.core.domain.model;

import java.util.UUID;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class User extends BaseEntity {

  private String username;

  public static User create(final String userName) {
    return builder().id(UUID.randomUUID()).username(userName).build();
  }
}
