package com.ffr.booklibrary.circulation.core.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class User extends BaseEntity {

  private String username;
}
