package solvd.laba.ermakovich.hf.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Account;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountRepository extends R2dbcRepository<Account, Long> {

    Mono<Boolean> existsByExternalId(UUID externalId);

    Mono<Account> findByExternalId(UUID employeeId);

}
