package com.sofka.broker.model.gateway;

import com.sofka.broker.model.User;
import reactor.core.publisher.Mono;

public interface UserGateway {
    Mono<Boolean> save(User user);
    Mono<User> exist(User user);
}
