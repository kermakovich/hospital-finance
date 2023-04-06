package solvd.laba.ermakovich.hf.mongo;

import reactor.core.publisher.Mono;

/**
 * @author Ermakovich Kseniya
 */
public interface SaveCustom<T> {

    Mono<T> save(T value);

}
