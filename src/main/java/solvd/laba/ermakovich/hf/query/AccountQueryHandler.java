package solvd.laba.ermakovich.hf.query;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.aggregate.AccountAggregate;
import solvd.laba.ermakovich.hf.domain.exception.ResourceDoesNotExistException;
import solvd.laba.ermakovich.hf.mongo.AccountRepository;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class AccountQueryHandler implements AccountQueryService {

    private final AccountRepository accountRepository;

    @Override
    public Mono<AccountAggregate> getByUserId(UUID userId) {
        return Mono.just(userId)
                .flatMap(accountRepository::findByUserId)
                .switchIfEmpty(Mono.error(new ResourceDoesNotExistException("user with id does not exist")));
    }

    @Override
    public Mono<AccountAggregate> findByIdOrCreate(String aggregateId) {
        return accountRepository.findById(aggregateId)
                .switchIfEmpty(
                        Mono.just(new AccountAggregate(aggregateId))
                );
    }

    @Override
    public Mono<Boolean> existsByUserId(UUID userId) {
        return accountRepository.existsByUserId(userId);
    }

    @Override
    public Mono<AccountAggregate> findById(String aggregateId) {
        return accountRepository.findById(aggregateId)
                .switchIfEmpty(Mono.error(new ResourceDoesNotExistException("account does not exist")));
    }

}

