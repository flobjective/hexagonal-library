package com.ffr.booklibrary.circulation.core.application.ports.outgoing;

import com.ffr.booklibrary.circulation.core.domain.model.User;

public interface UserRepository {

  User insert(final User userId);
}
