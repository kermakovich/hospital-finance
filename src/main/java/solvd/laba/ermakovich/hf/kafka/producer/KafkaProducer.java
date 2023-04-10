package solvd.laba.ermakovich.hf.kafka.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
@Component
public class KafkaProducer {

    private final KafkaSender<String, EventRoot> sender;

    public void send(final EventRoot value) {
        sender.send(
                Mono.just(
                        SenderRecord.create("saga",
                                0,
                                System.currentTimeMillis(),
                                value.getEventType(),
                                value,
                                null)
                )
        ).subscribe();
    }

}
