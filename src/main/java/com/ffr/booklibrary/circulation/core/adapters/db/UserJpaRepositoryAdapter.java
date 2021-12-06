package com.ffr.booklibrary.circulation.core.adapters.db;

import com.ffr.booklibrary.circulation.core.application.ports.outgoing.UserRepository;
import com.ffr.booklibrary.circulation.core.domain.model.User;
import javax.inject.Singleton;
import lombok.AllArgsConstructor;

@Singleton
@AllArgsConstructor
public class UserJpaRepositoryAdapter implements UserRepository {

  private UserJpaRepository userJpaRepository;

  @Override
  public User insert(final User user) {
    return this.userJpaRepository.save(JpaUser.of(user)).toUser();
  }
}
