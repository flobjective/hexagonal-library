package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import io.micronaut.core.annotation.Introspected;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Introspected
@Data
public class IssuedToUserIdDto {

  @NotEmpty @UUIDValidate private String userId;

  public UserId toUserId() {
    return new UserId(UUID.fromString(userId));
  }
}
