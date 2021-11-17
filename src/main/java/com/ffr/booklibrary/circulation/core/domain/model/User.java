package com.ffr.booklibrary.circulation.core.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@SuperBuilder
public class User extends BaseEntity {

  private String username;

  public static User create(final String userName) {
    return builder().id(UUID.randomUUID()).username(userName).build();
  }
}
