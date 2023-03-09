package solvd.laba.ermakovich.hf.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hf.domain.exception.ResourceDoesNotExistException;
import solvd.laba.ermakovich.hf.repository.AccountRepository;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.service.AccountService;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Mono<Account> getByExternalId(UUID employeeId) {
        return Mono.just(employeeId)
                .flatMap(accountRepository::findByExternalId)
                .switchIfEmpty(Mono.error(new ResourceDoesNotExistException("user with id does not exist")));
    }

    @Override
    public Mono<Account> create(UUID employeeUuid) {
        Account account = new Account(employeeUuid, genAccountNumber());
        return accountRepository.existsByExternalId(employeeUuid)
                .flatMap(isExist -> {
                    if(!isExist)
                        return accountRepository.save(account);
                    else
                        throw new ResourceAlreadyExistsException("this employee already has account");
        });
    }

    @SneakyThrows
    private String genAccountNumber() {
        StringBuilder result = new StringBuilder();
        var random = SecureRandom.getInstanceStrong();
        for (int i = 0; i < 12; i++) {
            result.append(random.nextInt(0, 9));
        }
        return result.toString();
    }

}
