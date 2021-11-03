package com.ffr.booklibrary.inventory.core.adapters.db;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(fluent = true)
public class JpaEvent extends JpaBaseEntity {

  public UUID getId() {
    return id;
  }

  @Id private UUID id;

  @Column(nullable = false)
  private String eventName;

  @Column(nullable = false, updatable = false)
  private Instant creationDate;

  @Column() private Instant publishedDate;

  @Type(type = "json")
  @Column(columnDefinition = "jsonb")
  private JsonNode eventPayload;

  static JpaEvent of(
      final UUID eventId, final String eventName, Instant publishedDate, JsonNode eventPayload) {
    return new JpaEvent(eventId, eventName, Instant.now(), publishedDate, eventPayload);
  }
}
