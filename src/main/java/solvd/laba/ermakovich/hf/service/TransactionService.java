package solvd.laba.ermakovich.hf.service;

import org.springframework.data.domain.PageRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Transaction;


/**
 * @author Ermakovich Kseniya
 */
public interface TransactionService {

    Flux<Transaction> getByAccountNumberAndPageable(String accountNumber, PageRequest pageable);

    Mono<Transaction> create(Transaction transaction);

}
