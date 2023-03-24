package solvd.laba.ermakovich.hf.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Transaction;
import solvd.laba.ermakovich.hf.domain.exception.IllegalOperationException;
import solvd.laba.ermakovich.hf.repository.TransactionRepository;
import solvd.laba.ermakovich.hf.service.AccountService;
import solvd.laba.ermakovich.hf.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ermakovich Kseniya
 */
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;


    @Override
    public Flux<Transaction> getByAccountNumberAndPageable(String accountNumber, PageRequest pageable) {
        return transactionRepository.findTopNByAccountNumber(accountNumber, pageable);
    }

    @Override
    @Transactional
    public Mono<Transaction> create(Transaction transaction) {
        return accountService.getByAccountNumber(transaction.getAccountNumber())
                .flatMap(account -> {
                    BigDecimal balance = account.getBalance();
                    if (balance.add(transaction.getAmount()).compareTo(BigDecimal.ZERO) <= 0)
                        throw new IllegalOperationException("Not enough money");
                    else {
                        transaction.setCreatedTime(LocalDateTime.now());
                        account.setBalance(balance.add(transaction.getAmount()));
                        accountService.update(account).subscribe();
                        return transactionRepository.save(transaction);
                    }
                });
    }
}
