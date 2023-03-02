package solvd.laba.ermakovich.hf.web.mapper;

import org.mapstruct.Mapper;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.web.dto.AccountDto;

/**
 * @author Ermakovich Kseniya
 */
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDto(Account account);

}
