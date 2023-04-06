package solvd.laba.ermakovich.hf.web.mapper;

import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.aggregate.AccountAggregate;
import solvd.laba.ermakovich.hf.web.dto.AccountAggregateDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountAggregateDto toDto(AccountAggregate account);

    default Mono<AccountAggregateDto> toDto(Mono<AccountAggregate> account) {
        return account.map(this::toDto);
    }

}
