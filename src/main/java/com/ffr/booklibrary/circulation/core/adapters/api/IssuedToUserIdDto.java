package com.ffr.booklibrary.circulation.core.adapters.api;

import com.ffr.booklibrary.circulation.core.model.UserId;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Introspected
@Data
public class IssuedToUserIdDto {

    @NotEmpty
    @UUIDValidate
    private String userId;

    public UserId toUserId() {
        return new UserId(UUID.fromString(userId));
    }
}
