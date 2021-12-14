package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import io.micronaut.core.annotation.Introspected;
import java.util.UUID;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Introspected
@AllArgsConstructor
public class ReturnBook {
  @NotEmpty @UUIDValidate private String userId;

  public UserId toUserId() {
    return new UserId(UUID.fromString(this.userId));
  }
}
