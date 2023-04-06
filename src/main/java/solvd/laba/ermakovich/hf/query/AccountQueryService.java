package solvd.laba.ermakovich.hf.query;

import java.util.UUID;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.aggregate.AccountAggregate;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountQueryService {

    Mono<AccountAggregate> getByUserId(UUID userId);

    Mono<AccountAggregate> findByIdOrCreate(String aggregateId);

    Mono<Boolean> existsByUserId(UUID userId);

    Mono<AccountAggregate> findById(String aggregateId);

}
