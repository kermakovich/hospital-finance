package solvd.laba.ermakovich.hf.aggregate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solvd.laba.ermakovich.hf.event.Event;
import solvd.laba.ermakovich.hf.event.account.CreateAccount;
import solvd.laba.ermakovich.hf.event.account.DeleteAccount;
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
    public void apply(CreateAccount event) {
        accountQueryService.findByIdOrCreate(event.getAggregateId())
                .flatMap(aggregate -> {
                    ((Event)event).copyTo(aggregate);
                    return accountRepository.save(aggregate);
                })
                .subscribe();
    }

    @Override
    public void apply(DeleteAccount eventRoot) {
             accountQueryService.findById(eventRoot.getAggregateId())
                    .flatMap(accountAggregate ->
                            accountRepository.deleteById(eventRoot.getAggregateId())
                    );
    }

}
