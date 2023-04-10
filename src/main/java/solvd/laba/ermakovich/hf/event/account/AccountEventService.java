package solvd.laba.ermakovich.hf.event.account;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountEventService {

    Mono<EventRoot> apply(EventRoot eventRoot);

    Mono<EventRoot> apply(CreateAccount event);

}
