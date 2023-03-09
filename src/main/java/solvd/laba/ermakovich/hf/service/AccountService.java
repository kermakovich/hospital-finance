package solvd.laba.ermakovich.hf.service;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Account;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountService {

    Mono<Account> getByExternalId(UUID employeeId);

    Mono<Account> create(UUID employeeUuid);

}
