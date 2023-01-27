package com.sofka.broker.api;

import com.sofka.broker.model.User;
import com.sofka.broker.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserService {
    private final UserUseCase userUseCase;

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Boolean> add(@RequestBody User user) {
        return userUseCase.save(user);
    }

    @PostMapping(path = "/exist", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Boolean> exist(@RequestBody User user) {
        return userUseCase.exist(user);
    }
}
