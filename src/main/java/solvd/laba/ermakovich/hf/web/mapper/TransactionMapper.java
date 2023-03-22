package solvd.laba.ermakovich.hf.web.mapper;

import org.mapstruct.Mapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Transaction;
import solvd.laba.ermakovich.hf.web.dto.TransactionDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toDto(Transaction transaction);

    Transaction toEntity(TransactionDto transactionDto);

    default Flux<TransactionDto> toDto(Flux<Transaction> transactionFlux) {
        return transactionFlux.map(this::toDto);
    }

    default Mono<TransactionDto> toDto(Mono<Transaction> transactionMono) {
        return transactionMono.map(this::toDto);
    }

}
