package solvd.laba.ermakovich.hf.aggregate;

import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.event.EventRoot;

/**
 * @author Ermakovich Kseniya
 */
public interface AccountAggregateService {

    Mono<Void> apply(EventRoot eventRoot);

}
