package com.ffr.booklibrary.inventory.core.adapters.event;

import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.context.annotation.Property;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.inject.Singleton;
import java.util.UUID;
import java.util.concurrent.Future;

@Singleton
public class KafkaEventSender {

  private final Producer<UUID, BaseDomainEvent> kafkaProducer;

  public KafkaEventSender(
      @KafkaClient(
              id = "book-producer",
              properties = {
                @Property(name = ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, value = "true"),
                @Property(name = ProducerConfig.ACKS_CONFIG, value = "all"),
                @Property(name = ProducerConfig.TRANSACTIONAL_ID_CONFIG, value = "BookSender-1"),
                @Property(
                    name = ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                    value = "org.apache.kafka.common.serialization.UUIDSerializer"),
                @Property(
                    name = ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                    value = "io.micronaut.configuration.kafka.serde.JsonSerde")
              })
          Producer<UUID, BaseDomainEvent> kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  public Future send(final BaseDomainEvent event) {
    return kafkaProducer.send(
         new ProducerRecord<>("books", event.eventId(), event));
  }
}
