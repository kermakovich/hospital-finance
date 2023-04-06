package solvd.laba.ermakovich.hf.mongo;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class SaveCustomImpl implements SaveCustom<EventRoot> {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<EventRoot> save(EventRoot eventRoot) {
        final String payload = eventRoot.getPayload();
        eventRoot.setPayload(payload);
        reactiveMongoTemplate.save(eventRoot).subscribe();
        return Mono.empty();
    }

}
