package solvd.laba.ermakovich.hf.aggregate;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.event.account.CreateAccount;
import solvd.laba.ermakovich.hf.event.account.DeleteAccount;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountAggregateService {

    Mono<Void> apply(CreateAccount eventRoot);

    Mono<Void> apply(DeleteAccount eventRoot);

}
