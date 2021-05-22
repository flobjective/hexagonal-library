package com.ffr.booklibrary.core.circulation.adapters.db;

import com.ffr.booklibrary.core.circulation.core.model.UserId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JpaUserId {

    @Id
    private UUID id;

    private String username;

    public UserId toUserId() {
        return UserId.builder().id(this.id).username(this.username).build();
    }
}
