package solvd.laba.ermakovich.hf.service;

import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
public interface UserClient {

    Mono<Boolean> isExistByExternalId(UUID employeeUuid);

}