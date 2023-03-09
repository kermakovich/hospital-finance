package solvd.laba.ermakovich.hf.web.mapper;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.web.dto.AccountDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDto(Account account);

    default Mono<AccountDto> toDto(Mono<Account> account) {
        return account.map(this::toDto);
    }

}
