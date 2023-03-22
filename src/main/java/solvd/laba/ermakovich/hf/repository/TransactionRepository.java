package solvd.laba.ermakovich.hf.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import solvd.laba.ermakovich.hf.domain.Transaction;


/**
 * @author Ermakovich Kseniya
 */
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    Flux<Transaction> findTopNByAccountNumber(String accountNumber, PageRequest pageable);

}
