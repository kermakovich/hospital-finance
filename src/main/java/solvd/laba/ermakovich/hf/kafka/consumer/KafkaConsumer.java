package solvd.laba.ermakovich.hf.kafka.consumer;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;
import solvd.laba.ermakovich.hf.event.EventRoot;
import solvd.laba.ermakovich.hf.event.account.AccountEventService;
import solvd.laba.ermakovich.hf.kafka.producer.KafkaProducer;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final KafkaReceiver<String, EventRoot> receiver;
    private final AccountEventService accountEventService;
    private final MessageMapper<EventRoot, EventRoot> messageMapper;
    private final KafkaProducer kafkaProducer;


    @PostConstruct
    public void fetch() {
        receiver.receive()
                .subscribe(r -> {
                    log.info("kafka consumer received message: {}", r.value());
                    EventRoot eventRoot = messageMapper.toEvent(r.value());
                    accountEventService.apply(eventRoot)
                            .map(event -> {
                                kafkaProducer.send(event);
                                return event;
                            })
                            .subscribe();
                    r.receiverOffset().acknowledge();
                });
    }
}