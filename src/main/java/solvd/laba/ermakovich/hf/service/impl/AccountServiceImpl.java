package solvd.laba.ermakovich.hf.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import solvd.laba.ermakovich.hf.AccountRepository;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hf.domain.exception.ResourceDoesNotExistException;
import solvd.laba.ermakovich.hf.service.AccountService;

import java.math.BigDecimal;
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
    public Account getInfo(UUID employeeId) {
        return accountRepository.findByExternalId(employeeId)
                .orElseThrow(() -> new ResourceDoesNotExistException("account does not exist"));
    }

    @Override
    public Account create(UUID employeeUuid) {
        Account account = new Account(employeeUuid, genAccountNumber());
        if (accountRepository.existsByExternalId(employeeUuid)) {
            throw new ResourceAlreadyExistsException("this employee already has account");
        }
        return accountRepository.save(account);
    }

    private String genAccountNumber() {
        StringBuilder result = new StringBuilder();
        var random = new Random();
        for (int i = 0; i < 12; i++) {
            result.append(new Random().nextInt(0, 9));
        }
        return result.toString();
    }

}
