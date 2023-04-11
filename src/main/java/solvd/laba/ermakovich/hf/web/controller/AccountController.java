package solvd.laba.ermakovich.hf.web.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.aggregate.AccountAggregate;
import solvd.laba.ermakovich.hf.command.AccountCommandService;
import solvd.laba.ermakovich.hf.command.CreateAccountCommand;
import solvd.laba.ermakovich.hf.query.AccountQueryService;
import solvd.laba.ermakovich.hf.web.dto.AccountAggregateDto;
import solvd.laba.ermakovich.hf.web.mapper.AccountMapper;

/**
 * @author Ermakovich Kseniya
 */
@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountCommandService commandService;
    private final AccountQueryService queryService;
    private final AccountMapper accountMapper;

    @GetMapping
    public Mono<AccountAggregateDto> retrieveInfo(@RequestParam UUID employeeUuid) {
        Mono<AccountAggregate> account = queryService.getByUserId(employeeUuid);
        return accountMapper.toDto(account);
    }

    @PostMapping
    public Mono<Void> create(@RequestBody UUID employeeUuid) {
        CreateAccountCommand command = new CreateAccountCommand(employeeUuid);
        return commandService.handle(command).then();
    }

}
