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
       return getResultEvent(event)
                .flatMap(eventRoot ->
                        saveCustom.save(eventRoot)
                           .flatMap(savedEvent -> {
                               if ("createAccountCompleted".equals(savedEvent.getEventType())) {
                                   return accountAggregateService.apply(event)
                                           .then(Mono.just(savedEvent));
                               } else {
                                   return Mono.just(savedEvent);
                               }
                       })
                );
    }


    @Override
    public Mono<EventRoot> getResultEvent(CreateAccount event) {
        return accountQueryService.existsByUserId(event.getAccount().getUserId())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.just(new CreateAccountResponseFactory()
                                .failed(event));
                    } else {
                        return Mono.just(new CreateAccountResponseFactory()
                                .success(event));
                    }
                });
    }

}


