package solvd.laba.ermakovich.hf.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.domain.exception.ResourceAlreadyExistsException;
import solvd.laba.ermakovich.hf.domain.exception.ResourceDoesNotExistException;
import solvd.laba.ermakovich.hf.service.AccountService;
import solvd.laba.ermakovich.hf.service.UserClient;
import solvd.laba.ermakovich.hf.web.dto.AccountDto;
import solvd.laba.ermakovich.hf.web.mapper.AccountMapper;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final UserClient userClient;
    private final AccountMapper accountMapper;

    @GetMapping
    public Mono<Account> retrieveInfo(@RequestParam UUID employeeUuid) {
        Mono<Boolean> isUserExist = userClient.isExistByExternalId(employeeUuid);
            return isUserExist.flatMap(isExist -> {
            if (Boolean.FALSE.equals(isExist))
                throw new ResourceDoesNotExistException("user does not exist");
            else
                return accountService.getByExternalId(employeeUuid);
        });
    }

    @PostMapping
    public ResponseEntity<Mono<AccountDto>> create(@RequestBody UUID employeeUuid) {
        Mono<Account> account = accountService.create(employeeUuid);
        return new ResponseEntity<>(accountMapper.toDto(account), HttpStatus.CREATED);
    }

}
