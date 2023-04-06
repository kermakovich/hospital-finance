package solvd.laba.ermakovich.hf.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hf.event.account.AccountEventService;
import solvd.laba.ermakovich.hf.event.account.CreateAccount;
import solvd.laba.ermakovich.hf.event.account.DeleteAccount;
import solvd.laba.ermakovich.hf.event.EventRoot;
import solvd.laba.ermakovich.hf.query.AccountQueryService;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class AccountCommandHandler implements AccountCommandService {

    private final AccountEventService eventService;
    private final AccountQueryService accountQueryService;

    @Override
    public Mono<Void> handle(CreateAccountCommand command) {
        return accountQueryService.existsByUserId(command.getUserId())
                .flatMap(isExist -> {
                    if (Boolean.FALSE.equals(isExist)) {
                        Account account = Account.builder()
                                .userId(command.getUserId())
                                .build();
                        EventRoot eventRoot = new CreateAccount(account, command.getAggregateId());
                        return eventService.when(eventRoot);
                    } else
                        throw new ResourceAlreadyExistsException("this employee already has account");
                });
    }

    @Override
    public Mono<Void> handle(DeleteAccountCommand command) {
        DeleteAccount event = new DeleteAccount(command.getAggregateId());
        return eventService.when(event);
    }

}
