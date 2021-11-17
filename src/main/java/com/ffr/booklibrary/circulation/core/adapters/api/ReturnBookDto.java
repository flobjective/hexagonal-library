package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@Introspected
@AllArgsConstructor
public class ReturnBookDto {
  @NotEmpty @UUIDValidate private String userId;

  public UserId toUserId() {
    return new UserId(UUID.fromString(this.userId));
  }
}
