package solvd.laba.ermakovich.hf.mongo;

import java.util.UUID;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.aggregate.AccountAggregate;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountRepository extends ReactiveMongoRepository<AccountAggregate, String> {

    Mono<Boolean> existsByUserId(UUID userId);

    Mono<AccountAggregate> findByUserId(UUID userId);

}
