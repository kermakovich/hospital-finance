package solvd.laba.ermakovich.hf.kafka;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.hf.event.account.CreateAccountEventRoot;
import solvd.laba.ermakovich.hf.event.account.DeleteAccountEventRoot;
import solvd.laba.ermakovich.hf.event.EventRoot;
import solvd.laba.ermakovich.hf.event.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
@Component
public class KafkaMessageMapper implements MessageMapper<EventRoot, IntegrationEvent> {

    @SneakyThrows
    public EventRoot toEvent(IntegrationEvent integrationEvent) {
        return switch (integrationEvent.getEventType()) {
            case CreateAccountEventRoot.EVENT_TYPE -> {
                Account account = Account.builder()
                        .userId(UUID.fromString(integrationEvent.getPayload()))
                        .build();
                yield CreateAccountEventRoot.builder()
                        .id(UUID.randomUUID().toString())
                        .account(account)
                        .aggregateId(UUID.randomUUID().toString())
                        .eventType(integrationEvent.getEventType())
                        .timeStamp(LocalDateTime.now())
                        .build();
            }
            case DeleteAccountEventRoot.EVENT_TYPE -> DeleteAccountEventRoot.builder()
                    .id(UUID.randomUUID().toString())
                    .aggregateId(integrationEvent.getPayload())
                    .eventType(integrationEvent.getEventType())
                    .timeStamp(LocalDateTime.now())
                    .build();
            default -> throw new IllegalOperationException("Wrong type was received from kafka broker");
        };
    }

}
