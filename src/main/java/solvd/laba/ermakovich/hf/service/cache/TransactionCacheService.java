package solvd.laba.ermakovich.hf.service.cache;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Transaction;
import solvd.laba.ermakovich.hf.domain.Transactions;


/**
 * @author Ermakovich Kseniya
 */
public interface TransactionCacheService {

    Flux<Transaction> getRecent(String accountNumber);

    Mono<Transactions> putRecent(String accountNumber);

}
