package solvd.laba.ermakovich.hf.command;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountCommandService {

    Mono<EventRoot> handle(CreateAccountCommand command);

    Mono<EventRoot> handle(DeleteAccountCommand command);

}
