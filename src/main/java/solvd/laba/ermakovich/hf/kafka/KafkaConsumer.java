package solvd.laba.ermakovich.hf.kafka;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;
import solvd.laba.ermakovich.hf.event.account.AccountEventService;
import solvd.laba.ermakovich.hf.event.EventRoot;
import solvd.laba.ermakovich.hf.event.IntegrationEvent;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final KafkaReceiver<String, IntegrationEvent> receiver;
    private final AccountEventService accountEventService;
    private final MessageMapper<EventRoot, IntegrationEvent> messageMapper;

    @PostConstruct
    public void fetch() {
        receiver.receive()
                .subscribe(r -> {
                    log.info("kafka consumer received message: {}", r.value());
                    EventRoot eventRoot = messageMapper.toEvent(r.value());
                    accountEventService.when(eventRoot).subscribe();
                    r.receiverOffset().acknowledge();
                });
    }
}