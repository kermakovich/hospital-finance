package solvd.laba.ermakovich.hf.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.TransactionSearchCriteria;
import solvd.laba.ermakovich.hf.domain.Transaction;
import solvd.laba.ermakovich.hf.service.TransactionService;
import solvd.laba.ermakovich.hf.service.cache.TransactionCacheService;
import solvd.laba.ermakovich.hf.web.dto.TransactionDto;
import solvd.laba.ermakovich.hf.web.dto.TransactionSearchCriteriaDto;
import solvd.laba.ermakovich.hf.web.mapper.TransactionCriteriaMapper;
import solvd.laba.ermakovich.hf.web.mapper.TransactionMapper;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionCacheService transactionCacheService;
    private final TransactionMapper transactionMapper;
    private final TransactionCriteriaMapper transactionCriteriaMapper;

    @GetMapping
    public Flux<TransactionDto> retrieveInfo(TransactionSearchCriteriaDto criteria) {
        TransactionSearchCriteria transactionCriteria = transactionCriteriaMapper.toEntity(criteria);
        Flux<Transaction> transactions;
        if (criteria.hasDefaultSettings()) {
            transactions = transactionCacheService.getRecent(criteria.getAccountNumber());
        } else {
            transactions = transactionService.getByAccountNumberAndPageable(criteria.getAccountNumber(),
                    PageRequest.of(transactionCriteria.getPage(),
                            transactionCriteria.getSize(),
                            Sort.by(transactionCriteria.getSort()).descending()));
        }
        return transactionMapper.toDto(transactions);
    }

    @PostMapping
    public Mono<TransactionDto> create(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        Mono<Transaction> transactionMono = transactionService.create(transaction)
                .doOnSuccess(succeedTransaction -> transactionCacheService
                        .putRecent(succeedTransaction.getAccountNumber())
                        .subscribe());
        return transactionMapper.toDto(transactionMono);
    }

}

