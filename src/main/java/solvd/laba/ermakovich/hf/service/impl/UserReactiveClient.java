package solvd.laba.ermakovich.hf.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import solvd.laba.ermakovich.hf.service.UserClient;

import java.util.UUID;

/**
 * @author Ermakovich Kseniya
 */
@RequiredArgsConstructor
@Service
public class UserReactiveClient implements UserClient {

    private final WebClient.Builder webClient;
    private static final String HOSPITAL_USERS_URL = "http://HOSPITAL-USERS";

    @Override
    public Mono<Boolean> isExistByExternalId(UUID employeeUuid) {
       return webClient.build()
                .get()
                .uri(HOSPITAL_USERS_URL + "/api/v1/users?externalId=" + employeeUuid)
                .retrieve()
                .bodyToMono(Boolean.class);
    }

}
