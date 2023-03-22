package solvd.laba.ermakovich.hf.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Transaction;
import solvd.laba.ermakovich.hf.service.TransactionService;
import solvd.laba.ermakovich.hf.web.dto.TransactionDto;
import solvd.laba.ermakovich.hf.web.dto.TransactionSearchCriteria;
import solvd.laba.ermakovich.hf.web.mapper.TransactionMapper;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public Flux<TransactionDto> retrieveInfo(TransactionSearchCriteria criteria) {
        Flux<Transaction> transactions = transactionService.getRecent(criteria.getAccountNumber(),
                PageRequest.of(criteria.getPage(),
                        criteria.getSize(),
                        Sort.by(criteria.getSort()).descending()));
        return transactionMapper.toDto(transactions);
    }

    @PostMapping
    public Mono<TransactionDto> create(@RequestBody TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        Mono<Transaction> transactionMono = transactionService.create(transaction);
        return transactionMapper.toDto(transactionMono);
    }

}

