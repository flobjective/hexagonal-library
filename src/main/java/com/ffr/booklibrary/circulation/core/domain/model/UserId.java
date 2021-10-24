package com.ffr.booklibrary.circulation.core.domain.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@EqualsAndHashCode
public class UserId {

  private UUID id;
}
