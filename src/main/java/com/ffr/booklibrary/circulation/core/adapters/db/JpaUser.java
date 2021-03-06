package com.ffr.booklibrary.circulation.core.adapters.db;

import com.ffr.booklibrary.circulation.core.domain.model.User;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaUser {

  @Id private UUID id;

  private String username;

  public static JpaUser of(final User user) {
    return new JpaUser(user.id(), user.getUsername());
  }

  public User toUser() {
    return User.builder().id(this.id).username(this.username).build();
  }
}
