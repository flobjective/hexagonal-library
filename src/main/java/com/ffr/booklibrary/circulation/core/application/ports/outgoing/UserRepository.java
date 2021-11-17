package com.ffr.booklibrary.circulation.core.application.ports.outgoing;

import com.ffr.booklibrary.circulation.core.domain.model.User;
import com.ffr.booklibrary.circulation.core.domain.model.UserId;
import java.util.Optional;

public interface UserRepository {

  User insert(final User userId);
}
