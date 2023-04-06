package solvd.laba.ermakovich.hf.event.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.aggregate.AccountAggregateService;
import solvd.laba.ermakovich.hf.event.EventRoot;
import solvd.laba.ermakovich.hf.mongo.SaveCustom;

/**
 * @author Ermakovich Kseniya
 */
@Component
@RequiredArgsConstructor
public class AccountEventHandler implements AccountEventService {

    private final SaveCustom<EventRoot> saveCustom;
    private final AccountAggregateService accountAggregateService;

    @Override
    public Mono<Void> when(EventRoot eventRoot) {
        saveCustom.save(eventRoot).subscribe();
        return accountAggregateService.apply(eventRoot);
    }

}
