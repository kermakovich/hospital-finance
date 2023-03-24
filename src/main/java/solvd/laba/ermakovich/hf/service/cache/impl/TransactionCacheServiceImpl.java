package solvd.laba.ermakovich.hf.service.cache.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import solvd.laba.ermakovich.hf.domain.Transaction;
import solvd.laba.ermakovich.hf.domain.Transactions;
import solvd.laba.ermakovich.hf.repository.TransactionRepository;
import solvd.laba.ermakovich.hf.service.cache.TransactionCacheService;

/**
 * @author Ermakovich Kseniya
 */
@Service
public class TransactionCacheServiceImpl implements TransactionCacheService {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final String DEFAULT_SORTING = "createdTime";
    private final TransactionRepository transactionRepository;
    private final ReactiveHashOperations<String, String, Transactions> cache;


    public TransactionCacheServiceImpl(TransactionRepository transactionRepository,
                                       final ReactiveRedisOperations<String, Transactions> operations) {
        this.transactionRepository = transactionRepository;
        this.cache = operations.opsForHash();
    }


    @Override
    public Flux<Transaction> getRecent(String accountNumber) {
        return this.cache.get(
                        "transactions",
                        accountNumber)
                .flatMapMany(mono -> Flux.fromIterable(mono.getList()));
    }


    @Override
    public Mono<Transactions> putRecent(String accountNumber) {
        return transactionRepository
                .findTopNByAccountNumber(accountNumber,
                        PageRequest.of(0,
                                DEFAULT_PAGE_SIZE,
                                Sort.by(DEFAULT_SORTING)
                                        .descending()))
                .collectList()
                .doOnSuccess(transactionList ->
                    this.cache.put(
                                    "transactions",
                                    accountNumber,
                                    new Transactions(transactionList))
                            .subscribeOn(Schedulers.boundedElastic()))
                .map(Transactions::new);
    }

}
