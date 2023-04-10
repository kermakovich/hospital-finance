package solvd.laba.ermakovich.hf.event.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.aggregate.AccountAggregateService;
import solvd.laba.ermakovich.hf.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.hf.event.EventRoot;
import solvd.laba.ermakovich.hf.mongo.SaveCustom;
import solvd.laba.ermakovich.hf.query.AccountQueryService;

/**
 * @author Ermakovich Kseniya
 */
@Component
@RequiredArgsConstructor
public class AccountEventHandler implements AccountEventService {

    private final SaveCustom<EventRoot> saveCustom;
    private final AccountAggregateService accountAggregateService;
    private final AccountQueryService accountQueryService;


    @Override
    public Mono<EventRoot> apply(EventRoot eventRoot) {
        saveCustom.save(eventRoot).subscribe();
        return switch (eventRoot.getEventType()) {
            case "CreateAccount" -> apply((CreateAccount) eventRoot);
            default -> throw new IllegalOperationException("wrong event type");
        };
    }


    @Override
    public Mono<EventRoot> apply(CreateAccount event) {
       return accountQueryService.existsByUserId(event.getAccount().getUserId())
                .flatMap(isExist -> {
                    EventRoot eventRoot;
                    if (Boolean.TRUE.equals(isExist)) {
                        eventRoot = new CreateAccountResponseFactory()
                                .getFailedEvent(event);
                    } else {
                        accountAggregateService.apply(event).subscribe();
                        eventRoot = new CreateAccountResponseFactory()
                                .getSucceedEvent(event);
                    }
                    saveCustom.save(eventRoot);
                    return Mono.just(eventRoot);
                });
    }


}
