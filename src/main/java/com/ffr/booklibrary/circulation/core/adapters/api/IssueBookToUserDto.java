package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.Transient;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Introspected
@Data
public class IssueBookToUserDto {

  @NotNull @UUIDValidate private final String userId;

  @Transient
  UserId toUserId() {
    return new UserId(UUID.fromString(this.userId));
  }
}
