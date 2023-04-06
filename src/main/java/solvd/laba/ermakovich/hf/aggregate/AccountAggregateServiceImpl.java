package solvd.laba.ermakovich.hf.aggregate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.event.account.DeleteAccount;
import solvd.laba.ermakovich.hf.event.Event;
import solvd.laba.ermakovich.hf.event.EventRoot;
import solvd.laba.ermakovich.hf.mongo.AccountRepository;
import solvd.laba.ermakovich.hf.query.AccountQueryService;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class AccountAggregateServiceImpl implements AccountAggregateService {

    private final AccountQueryService accountQueryService;
    private final AccountRepository accountRepository;

    @Override
    public Mono<Void> apply(EventRoot eventRoot) {
        if (DeleteAccount.EVENT_TYPE.equals(eventRoot.getEventType())) {
            return accountQueryService.findById(eventRoot.getAggregateId())
                    .flatMap(accountAggregate ->
                            accountRepository.deleteById(eventRoot.getAggregateId())
                    );
        } else {
            return accountQueryService.findByIdOrCreate(eventRoot.getAggregateId())
                    .flatMap(aggregate -> {
                        ((Event)eventRoot).copyTo(aggregate);
                        return accountRepository.save(aggregate);
                    }).then();
        }
    }

}
