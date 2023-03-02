package solvd.laba.ermakovich.hf.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.json.AbstractJackson2Decoder;
import org.springframework.web.bind.annotation.*;
import solvd.laba.ermakovich.hf.domain.Account;
import solvd.laba.ermakovich.hf.service.AccountService;
import solvd.laba.ermakovich.hf.web.dto.AccountDto;
import solvd.laba.ermakovich.hf.web.mapper.AccountMapper;

import java.util.Random;
import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping
    public AccountDto retrieveInfo(@RequestParam UUID employeeUuid) {
        Account account = accountService.getInfo(employeeUuid);
        return accountMapper.toDto(account);
    }

    @PostMapping
    public AccountDto create(@RequestBody UUID employeeUuid) {
        Account account = accountService.create(employeeUuid);
        return accountMapper.toDto(account);
    }

}
