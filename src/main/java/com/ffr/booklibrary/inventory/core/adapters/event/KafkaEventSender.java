package com.ffr.booklibrary.inventory.core.adapters.event;

import com.ffr.booklibrary.shared.events.BaseDomainEvent;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.context.annotation.Property;
import java.util.UUID;
import java.util.concurrent.Future;
import javax.inject.Singleton;

import io.micronaut.context.annotation.Value;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

@Singleton
public class KafkaEventSender {

  private final Producer<UUID, BaseDomainEvent> kafkaProducer;

  @Value("${inventory.kafka.topic}")
  private String topicName;

  public KafkaEventSender(
      @KafkaClient(
              id = "inventory-book-producer",
              properties = {
                @Property(name = ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, value = "true"),
                @Property(name = ProducerConfig.ACKS_CONFIG, value = "all"),
                @Property(name = ProducerConfig.TRANSACTIONAL_ID_CONFIG, value = "inventory-book-1"),
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

  public Future<RecordMetadata> send(final BaseDomainEvent event) {
    return kafkaProducer.send(new ProducerRecord(this.topicName, event.eventId(), event));
  }
}
