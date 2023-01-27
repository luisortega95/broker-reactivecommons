package com.sofka.broker.adapter;

import com.sofka.broker.model.User;
import com.sofka.broker.model.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.api.AsyncQuery;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@EnableDirectAsyncGateway
@RequiredArgsConstructor
public class UserMessageAsyncAdapter implements UserGateway {

    private final DirectAsyncGateway asyncGateway;

    @Override
    public Mono<Boolean> save(User user) {
        String uuid = UUID.randomUUID().toString();
        final Command<User> command = new Command<>("user.save", uuid, user);
        return asyncGateway
                .sendCommand(command, "users.operations")
                .thenReturn(Boolean.TRUE);
    }

    @Override
    public Mono<User> exist(User user) {
        final AsyncQuery<User> query = new AsyncQuery<>("user.exist", user);
        return asyncGateway
                .requestReply(query, "users.operations", User.class)
                .map(us -> {
                    return us;
                })
                .switchIfEmpty(Mono.empty())
                .onErrorResume(e -> {
                    return Mono.just(new User());
                });

    }
}
