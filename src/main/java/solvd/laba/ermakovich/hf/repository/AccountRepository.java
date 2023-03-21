package solvd.laba.ermakovich.hf.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Account;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

    Mono<Boolean> existsByExternalId(UUID externalId);

    Mono<Account> findByExternalId(UUID employeeId);

}
