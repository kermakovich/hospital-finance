package solvd.laba.ermakovich.hf.command;

import reactor.core.publisher.Mono;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountCommandService {

    Mono<Void> handle(CreateAccountCommand command);

    Mono<Void> handle(DeleteAccountCommand command);

}
