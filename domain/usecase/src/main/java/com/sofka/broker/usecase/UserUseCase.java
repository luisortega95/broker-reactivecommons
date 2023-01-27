package com.sofka.broker.usecase;

import com.sofka.broker.model.User;
import com.sofka.broker.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;

    public Mono<Boolean> save(User user) {
        return userGateway.save(user);
    }

    public Mono<Boolean> exist(User user) {
        return userGateway.exist(user).thenReturn(Boolean.TRUE);
    }
}
