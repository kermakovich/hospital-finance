package solvd.laba.ermakovich.hf.kafka;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.kafka.receiver.KafkaReceiver;
import solvd.laba.ermakovich.hf.service.AccountService;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final KafkaReceiver<String, UUID> receiver;
    private final AccountService accountService;

    @PostConstruct
    public void fetch() {
        receiver.receive()
                .subscribe(r -> {
                    log.info("kafka consumer received message: {}", r.value());
                    accountService.create(r.value()).subscribe();
                    r.receiverOffset().acknowledge();
                });
    }
}